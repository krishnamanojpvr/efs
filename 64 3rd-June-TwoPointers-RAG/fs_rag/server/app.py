from flask import Flask, request, jsonify
from huggingface_hub import InferenceClient
from flask_cors import CORS
from werkzeug.security import generate_password_hash, check_password_hash
from werkzeug.utils import secure_filename
from PyPDF2 import PdfReader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain_community.embeddings import HuggingFaceEmbeddings
import faiss
import pickle
import uuid
import os
import numpy as np
import requests
import jwt as pyjwt
from functools import wraps
from datetime import datetime, timedelta, timezone
from pymongo import MongoClient

app = Flask(__name__)
CORS(app)


@app.route('/')
def index():
    return 'Flask server is running.'


# MongoDB configuration
MONGO_URI = os.getenv('MONGO_URI', 'mongodb://localhost:27017/')
mongo_client = MongoClient(MONGO_URI)
db = mongo_client['fs_rag']
users_collection = db['users']

# Helper to get user by username


def get_user_by_username(username):
    return users_collection.find_one({'username': username})

# Helper to create user (for initial setup, not exposed as endpoint)


def create_user(username, password, role):
    if get_user_by_username(username):
        return False
    hashed = generate_password_hash(password)
    users_collection.insert_one({
        'id': str(uuid.uuid4()),
        'username': username,
        'password': hashed,
        'role': role
    })
    return True

# Example: Uncomment to create default users on first run
# create_user('admin', 'admin123', 'admin')
# create_user('user', 'user123', 'user')


SECRET_KEY = os.getenv('JWT_SECRET', 'dev_secret_key')
JWT_EXP_DELTA_SECONDS = 3600


def generate_jwt(user):
    payload = {
        'id': user['id'],
        'username': user['username'],
        'role': user['role'],
        'exp': datetime.now(timezone.utc) + timedelta(seconds=JWT_EXP_DELTA_SECONDS)
    }
    # PyJWT >= 2.x returns a string, but some installs may return bytes. Ensure string.
    token = pyjwt.encode(payload, SECRET_KEY, algorithm='HS256')
    if isinstance(token, bytes):
        token = token.decode('utf-8')
    return token


def decode_jwt(token):
    try:
        return pyjwt.decode(token, SECRET_KEY, algorithms=['HS256'])
    except pyjwt.ExpiredSignatureError:
        return None
    except pyjwt.InvalidTokenError:
        return None


def jwt_required(roles=None):
    def decorator(f):
        @wraps(f)
        def wrapper(*args, **kwargs):
            auth_header = request.headers.get('Authorization', None)
            if not auth_header or not auth_header.startswith('Bearer '):
                return jsonify({'message': 'Missing or invalid token'}), 401
            token = auth_header.split(' ')[1]
            payload = decode_jwt(token)
            if not payload:
                return jsonify({'message': 'Invalid or expired token'}), 401
            if roles and payload.get('role') not in roles:
                return jsonify({'message': 'Unauthorized'}), 403
            request.user = payload
            return f(*args, **kwargs)
        return wrapper
    return decorator


@app.route('/login', methods=['POST'])
def login():
    data = request.json
    username = data.get('username')
    password = data.get('password')
    user = get_user_by_username(username)
    if user and check_password_hash(user['password'], password):
        token = generate_jwt(user)
        return jsonify({
            'token': token,
            'role': user['role'],
            'username': user['username'],
            'message': 'Login successful'
        })
    return jsonify({'message': 'Invalid credentials'}), 401


@app.route('/register', methods=['POST'])
def register():
    data = request.json
    username = data.get('username')
    password = data.get('password')
    role = data.get('role', 'user')
    if not username or not password:
        return jsonify({'message': 'Username and password required'}), 400
    if get_user_by_username(username):
        return jsonify({'message': 'Username already exists'}), 409
    create_user(username, password, role)
    return jsonify({'message': 'Registration successful'}), 201


UPLOAD_FOLDER = 'uploads'
os.makedirs(UPLOAD_FOLDER, exist_ok=True)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER

VECTOR_STORE_PATH = 'vector_store.faiss'
METADATA_STORE_PATH = 'vector_store_meta.pkl'


@app.route('/upload_pdf', methods=['POST'])
@jwt_required(roles=['admin'])
def upload_pdf():
    if 'file' not in request.files:
        return jsonify({'message': 'No file part'}), 400
    file = request.files['file']
    if file.filename == '':
        return jsonify({'message': 'No selected file'}), 400
    filename = secure_filename(file.filename)
    filepath = os.path.join(app.config['UPLOAD_FOLDER'], filename)
    file.save(filepath)
    # Extract text from PDF
    reader = PdfReader(filepath)
    text = ''
    for page in reader.pages:
        text += page.extract_text() or ''
    # Split text into chunks
    splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50, strip_whitespace=True,
                                              separators=[
                                                  "\n#{1,6}", "```\n", "\n\\\*+\n", "\n---+\n", "\n_+\n", "\n\n", "\n", " ", ""
                                              ],)
    docs = splitter.split_text(text)
    # Generate embeddings
    embedder = HuggingFaceEmbeddings(
        model_name="sentence-transformers/all-MiniLM-L6-v2", encode_kwargs={"normalize_embeddings": True})
    vectors = embedder.embed_documents(docs)
    # Create FAISS index
    index = faiss.IndexFlatL2(len(vectors[0]))
    index.add(np.array(vectors).astype('float32'))
    # Save index and metadata
    faiss.write_index(index, VECTOR_STORE_PATH)
    with open(METADATA_STORE_PATH, 'wb') as f:
        pickle.dump(docs, f)
    return jsonify({'message': 'PDF uploaded, text extracted, and vector store created', 'chunks': len(docs)})


# Example LLM endpoint
HF_API_URL = "https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.2"
# Set your Hugging Face token as an environment variable
HF_API_TOKEN = os.getenv("HF_API_TOKEN")
client = InferenceClient(
    # model="mistralai/Mistral-7B-Instruct-v0.3",
    token=HF_API_TOKEN
)


@app.route('/query', methods=['POST'])
@jwt_required(roles=['user', 'admin'])
def query():
    try:
        data = request.json
        user_query = data.get('query')
        if not user_query:
            return jsonify({'message': 'No query provided'}), 400
        # Load FAISS index and docs
        if not os.path.exists(VECTOR_STORE_PATH) or not os.path.exists(METADATA_STORE_PATH):
            return jsonify({'message': 'No vector store found. Please upload a PDF first.'}), 400
        index = faiss.read_index(VECTOR_STORE_PATH)
        with open(METADATA_STORE_PATH, 'rb') as f:
            docs = pickle.load(f)
        # Embed user query
        embedder = HuggingFaceEmbeddings(
            model_name="sentence-transformers/all-MiniLM-L6-v2")
        query_vec = embedder.embed_query(user_query)
        # Similarity search
        D, I = index.search(np.array([query_vec]).astype('float32'), k=3)
        context = "\n".join([docs[i] for i in I[0]])
        # Call Hugging Face LLM
        prompt = f"""Using the information in the context,
        Give a comprehensive answer to the question.
        Respond only to the question asked, response should be concise and relevant to the question and it should reflect the context, meaning give answers relavant to context provided.
        If the answer cannot be deduced from the context, do not give an answer(Do not dirctly say that there is no context  or no information, but say politely).

        Context:
        {context}
        ---
        Now here is the Question you need to answer.
        Question: {user_query}
        """
        messages = [{"role": "user", "content": prompt}]
        completion = client.chat.completions.create(
        model="Qwen/Qwen2.5-Coder-32B-Instruct",
        messages=messages,
        max_tokens=500
    )
        response = completion.choices[0].message["content"]
        return jsonify({
            'answer': response,
            'context': context,
            "ok": True,
        })
    except Exception as e:
        print(e)
        return jsonify({'message': str(e), 'ok': False}), 500


# TODO: Implement admin login, PDF upload, user login, and query endpoints


if __name__ == '__main__':
    app.run(debug=True)
