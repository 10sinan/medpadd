import { useUIStore } from '../store/store';
import { NavLink, useNavigate } from 'react-router-dom';
import './Sidebar.css';

function Sidebar() {
  const { sidebarOpen, toggleSidebar } = useUIStore();
  const navigate = useNavigate();

  const menuItems = [
    { path: '/', label: '🏠 Ana Sayfa' },
    { path: '/explore', label: '🔍 Keşfet' },
    { path: '/creators', label: '👨‍🎨 İçerik Üreticileri' },
    { path: '/my-contents', label: '📚 Benim İçeriklerim' },
    { path: '/subscriptions', label: '💳 Abonelikler' },
    { path: '/following', label: '⭐ Takip Ettiklerim' },
    { path: '/badges', label: '🏅 Rozetlerim' },
    { path: '/profile', label: '👤 Profilim' },
  ];

  const handleCreateClick = () => {
    navigate('/create-content');
    if (window.innerWidth <= 768) {
      toggleSidebar();
    }
  };

  return (
    <>
      <button className="sidebar-toggle" onClick={toggleSidebar}>
        ☰
      </button>

      <aside className={`sidebar ${sidebarOpen ? 'open' : 'closed'}`}>
        <div className="sidebar-header">
          <button className="sidebar-close" onClick={toggleSidebar}>
            ✕
          </button>
        </div>

        <button className="create-button-sidebar" onClick={handleCreateClick}>
          + Yeni İçerik
        </button>

        <nav className="sidebar-nav">
          {menuItems.map((item) => (
            <NavLink
              key={item.path}
              to={item.path}
              className={({ isActive }) => `sidebar-link ${isActive ? 'active' : ''}`}
              onClick={() => {
                if (window.innerWidth <= 768) {
                  toggleSidebar();
                }
              }}
            >
              {item.label}
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-footer">
          <p className="sidebar-info">© 2025 MedPadd</p>
        </div>
      </aside>

      {sidebarOpen && window.innerWidth <= 768 && (
        <div className="sidebar-overlay" onClick={toggleSidebar}></div>
      )}
    </>
  );
}


export default Sidebar;
