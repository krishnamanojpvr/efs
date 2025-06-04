import { useContext, useState } from 'react';
import { AuthContext } from './AuthContext';

export default function UserQuery() {
  const { auth } = useContext(AuthContext);
  const [query, setQuery] = useState('');
  const [answer, setAnswer] = useState('');
  const [context, setContext] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleQuery = async (e) => {
    e.preventDefault();
    setAnswer('');
    setContext('');
    setError('');
    setLoading(true);
    try {
      const res = await fetch('http://localhost:5000/query', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${auth.token}`
        },
        body: JSON.stringify({ query })
      });
      const data = await res.json();
      if (res.ok) {
        setAnswer(data.answer);
        setContext(data.context);
      } else {
        setError(data.message || 'Query failed');
      }
    } catch {
      setError('Network error');
    }
    setLoading(false);
  };

  return (
    <div className="bg-white p-6 rounded shadow-md max-w-md mx-auto mt-8">
      <h2 className="text-xl font-bold mb-4">User Query</h2>
      <form onSubmit={handleQuery} className="flex flex-col gap-4">
        <input
          type="text"
          placeholder="Ask a question..."
          value={query}
          onChange={e => setQuery(e.target.value)}
          required
          className="border rounded px-3 py-2"
        />
        <button type="submit" disabled={loading} className="bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition">{loading ? 'Searching...' : 'Ask'}</button>
      </form>
      {error && <p className="text-red-600 mt-2 text-center">{error}</p>}
      {answer && (
        <div className="mt-4">
          <h3 className="font-semibold">Answer</h3>
          <p className="mb-2">{answer}</p>
          <details>
            <summary className="cursor-pointer text-blue-600">Context</summary>
            <pre className="bg-gray-100 p-2 rounded text-xs whitespace-pre-wrap">{context}</pre>
          </details>
        </div>
      )}
    </div>
  );
}
