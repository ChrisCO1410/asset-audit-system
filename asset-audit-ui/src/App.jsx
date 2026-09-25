import { useState, useEffect } from 'react';
import Login from './components/Login';
import { getAssets } from './services/api';

export default function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(
      !!localStorage.getItem('token')
  );
  const [assets, setAssets] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => {
    if (!isAuthenticated) return;

    let isMounted = true;

    const fetchAssetsData = async () => {
      try {
        setLoading(true);
        const data = await getAssets();
        if (isMounted) {
          setAssets(data);
        }
      } catch {
        if (isMounted) {
          setError('Failed to load asset inventory.');
        }
      } finally {
        if (isMounted) {
          setLoading(false);
        }
      }
    };

    void fetchAssetsData();

    return () => {
      isMounted = false;
    };
  }, [isAuthenticated]);

  const handleLogout = () => {
    localStorage.removeItem('token');
    setIsAuthenticated(false);
    setAssets([]);
  };

  if (!isAuthenticated) {
    return <Login onLoginSuccess={() => setIsAuthenticated(true)} />;
  }

  return (
      <div className="min-h-screen bg-slate-900 text-white p-8">
        <div className="max-w-6xl mx-auto">
          <header className="flex justify-between items-center mb-8 border-b border-slate-700 pb-4">
            <div>
              <h1 className="text-3xl font-bold text-blue-400">Asset & Compliance Dashboard</h1>
              <p className="text-sm text-slate-400">Enterprise Audit System</p>
            </div>
            <button
                onClick={handleLogout}
                className="bg-red-600 hover:bg-red-500 px-4 py-2 rounded text-sm font-semibold transition-colors"
            >
              Logout
            </button>
          </header>

          {loading && <p className="text-slate-400">Loading assets...</p>}
          {error && <p className="text-red-400">{error}</p>}

          {!loading && !error && (
              <div className="bg-slate-800 rounded-lg border border-slate-700 overflow-hidden shadow-lg">
                <table className="w-full text-left border-collapse">
                  <thead>
                  <tr className="bg-slate-700/50 text-slate-300 text-sm uppercase border-b border-slate-700">
                    <th className="py-3 px-4">Serial Number</th>
                    <th className="py-3 px-4">Asset Name</th>
                    <th className="py-3 px-4">Category</th>
                    <th className="py-3 px-4">Status</th>
                    <th className="py-3 px-4">Next Calibration</th>
                  </tr>
                  </thead>
                  <tbody className="divide-y divide-slate-700/50 text-sm">
                  {assets.map((asset) => (
                      <tr key={asset.id || asset.serialNumber} className="hover:bg-slate-700/30 transition-colors">
                        <td className="py-3 px-4 font-mono text-blue-300">{asset.serialNumber}</td>
                        <td className="py-3 px-4 font-medium">{asset.name}</td>
                        <td className="py-3 px-4 text-slate-300">{asset.category}</td>
                        <td className="py-3 px-4">
                      <span className={`inline-block px-2 py-1 rounded text-xs font-semibold ${
                          asset.status === 'AVAILABLE'
                              ? 'bg-emerald-500/20 text-emerald-400 border border-emerald-500/30'
                              : 'bg-amber-500/20 text-amber-400 border border-amber-500/30'
                      }`}>
                        {asset.status}
                      </span>
                        </td>
                        <td className="py-3 px-4 text-slate-300">{asset.nextCalibrationDate}</td>
                      </tr>
                  ))}
                  </tbody>
                </table>
              </div>
          )}
        </div>
      </div>
  );
}