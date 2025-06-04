import { useContext, useState } from 'react';
import { AuthContext } from './AuthContext';

export default function AdminUpload() {
  const { auth } = useContext(AuthContext);
  const [file, setFile] = useState(null);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(false);

  const handleUpload = async (e) => {
    e.preventDefault();
    setMessage('');
    setLoading(true);
    const formData = new FormData();
    formData.append('file', file);
    try {
      const res = await fetch('http://localhost:5000/upload_pdf', {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${auth.token}` },
        body: formData
      });
      const data = await res.json();
      setMessage(data.message || (res.ok ? 'Upload successful' : 'Upload failed'));
    } catch {
      setMessage('Network error');
    }
    setLoading(false);
  };

  return (
    <div className="bg-white p-6 rounded shadow-md max-w-md mx-auto mt-8">
      <h2 className="text-xl font-bold mb-4">Admin PDF Upload</h2>
      <form onSubmit={handleUpload} className="flex flex-col gap-4">
        <input type="file" accept="application/pdf" onChange={e => setFile(e.target.files[0])} required className="border rounded px-3 py-2" />
        <button type="submit" disabled={loading} className="bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition">{loading ? 'Uploading...' : 'Upload'}</button>
      </form>
      {message && <p className="mt-2 text-center text-blue-700">{message}</p>}
    </div>
  );
}
