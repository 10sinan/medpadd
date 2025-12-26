import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuthStore } from '../store/auth'

export default function DashboardLayout({ children }) {
  const [sidebarOpen, setSidebarOpen] = useState(true)
  const navigate = useNavigate()
  const { user, logout } = useAuthStore()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <div className="dashboard">
      {/* Navbar */}
      <nav className="dashboard-navbar">
        <div className="navbar-content">
          <button
            className="sidebar-toggle"
            onClick={() => setSidebarOpen(!sidebarOpen)}
            title={sidebarOpen ? 'Sidebar kapat' : 'Sidebar aç'}
          >
            ☰
          </button>
          <h1 className="navbar-title">MedPadd</h1>
          <div className="navbar-user">
            <span>{user?.firstName || 'Kullanıcı'}</span>
            <button className="btn-logout" onClick={handleLogout}>
              Çıkış
            </button>
          </div>
        </div>
      </nav>

      <div className="dashboard-container">
        {/* Sidebar */}
        <aside className={`sidebar ${sidebarOpen ? 'open' : 'closed'}`}>
          <div className="sidebar-menu">
            <button
              className="sidebar-item"
              onClick={() => navigate('/dashboard')}
            >
              🏠 Ana Sayfa
            </button>
            <button
              className="sidebar-item"
              onClick={() => navigate('/dashboard/explore')}
            >
              🔍 Keşfet
            </button>
            <button
              className="sidebar-item"
              onClick={() => navigate('/dashboard/profile')}
            >
              👤 Profil
            </button>
          </div>
        </aside>

        {/* Main Content */}
        <main className={`dashboard-main ${sidebarOpen ? 'with-sidebar' : 'full-width'}`}>
          {children}
        </main>
      </div>
    </div>
  )
}
