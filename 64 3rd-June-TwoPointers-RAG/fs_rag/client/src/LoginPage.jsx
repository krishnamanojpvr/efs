import { useContext, useState } from 'react';
import { AuthContext } from './AuthContext';

export default function LoginPage() {
  const { login } = useContext(AuthContext);
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [registerMode, setRegisterMode] = useState(false);
  const [role, setRole] = useState('user');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSuccess('');
    if (registerMode) {
      // Registration
      try {
        const res = await fetch('http://localhost:5000/register', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username, password, role })
        });
        const data = await res.json();
        if (res.ok) {
          setSuccess('Registration successful! You can now log in.');
          setRegisterMode(false);
        } else {
          setError(data.message || 'Registration failed');
        }
      } catch (err) {
        setError('Network error');
      }
    } else {
      // Login
      try {
        const res = await fetch('http://localhost:5000/login', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ username, password })
        });
        const data = await res.json();
        if (res.ok) {
          login(data);
          window.location.reload();
        } else {
          setError(data.message || 'Login failed');
        }
      } catch (err) {
        setError('Network error');
      }
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50">
      <div className="bg-white p-8 rounded shadow-md w-full max-w-sm">
        <h2 className="text-2xl font-bold mb-4 text-center">{registerMode ? 'Register' : 'Login'}</h2>
        <form onSubmit={handleSubmit} className="space-y-4">
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={e => setUsername(e.target.value)}
            required
            className="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
            className="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
          />
          {registerMode && (
            <select
              value={role}
              onChange={e => setRole(e.target.value)}
              className="w-full px-3 py-2 border rounded focus:outline-none focus:ring focus:border-blue-300"
            >
              <option value="user">User</option>
              <option value="admin">Admin</option>
            </select>
          )}
          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition"
          >
            {registerMode ? 'Register' : 'Login'}
          </button>
        </form>
        <div className="flex justify-between mt-4">
          <button
            className="text-blue-600 hover:underline text-sm"
            onClick={() => { setRegisterMode(!registerMode); setError(''); setSuccess(''); }}
          >
            {registerMode ? 'Already have an account? Login' : "Don't have an account? Register"}
          </button>
        </div>
        {error && <p className="text-red-600 mt-2 text-center">{error}</p>}
        {success && <p className="text-green-600 mt-2 text-center">{success}</p>}
      </div>
    </div>
  );
}
