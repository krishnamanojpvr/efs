import { useContext } from 'react';
import { AuthContext } from './AuthContext';
import LoginPage from './LoginPage';
import AdminUpload from './AdminUpload';
import UserQuery from './UserQuery';
import './App.css';

function App() {
  const { auth, logout } = useContext(AuthContext);

  if (!auth) return <LoginPage />;

  return (
    <div className="min-h-screen bg-gray-50">
      <header className="bg-blue-700 text-white py-4 px-6 flex justify-between items-center shadow">
        <h1 className="text-2xl font-bold">FS RAG Demo</h1>
        <div className="flex items-center gap-4">
          <span className="font-medium">Welcome, {auth.username} <span className="italic">({auth.role})</span></span>
          <button onClick={logout} className="bg-white text-blue-700 px-3 py-1 rounded hover:bg-blue-100 transition">Logout</button>
        </div>
      </header>
      <main className="max-w-2xl mx-auto py-8">
        {auth.role === 'admin' && <AdminUpload />}
        <UserQuery />
      </main>
    </div>
  );
}

export default App;
