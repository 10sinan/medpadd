import { useAuthStore, useUIStore } from '../store/store';
import './Navbar.css';
import { useNavigate } from 'react-router-dom';
import SearchBar from './SearchBar';

function Navbar() {
  const { user, logout } = useAuthStore();
  const { toggleDarkMode, darkMode } = useUIStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <div className="navbar-brand">
          <h1>📚 MedPadd</h1>
          <span className="navbar-tagline">Sanat & Edebiyat Platformu</span>
        </div>

        {user && <SearchBar />}

        <div className="navbar-right">
          <button 
            className="navbar-btn theme-toggle" 
            onClick={toggleDarkMode}
            title={darkMode ? 'Açık mod' : 'Koyu mod'}
          >
            {darkMode ? '☀️' : '🌙'}
          </button>

          {user ? (
            <div className="navbar-user">
              <span className="navbar-username">{user.firstName} {user.lastName}</span>
              <button className="navbar-btn logout-btn" onClick={handleLogout}>
                Çıkış Yap
              </button>
            </div>
          ) : (
            <button 
              className="navbar-btn login-btn"
              onClick={() => navigate('/login')}
            >
              Giriş Yap
            </button>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
