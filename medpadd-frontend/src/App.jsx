import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { useAuthStore, useUIStore } from './store/store';
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import ContentDetailPage from './pages/ContentDetailPage';
import CreatorsPage from './pages/CreatorsPage';
import CreatorDetailPage from './pages/CreatorDetailPage';
import ProfilePage from './pages/ProfilePage';
import MyContentsPage from './pages/MyContentsPage';
import CreateContentPage from './pages/CreateContentPage';
import SubscriptionsPage from './pages/SubscriptionsPage';
import FollowingPage from './pages/FollowingPage';
import BadgesPage from './pages/BadgesPage';
import './App.css';

function App() {
  const { user } = useAuthStore();
  const { darkMode } = useUIStore();

  return (
    <Router>
      <div className={`app ${darkMode ? 'dark-mode' : 'light-mode'}`}>
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          
          <Route
            path="/*"
            element={
              user ? (
                <div className="app-layout">
                  <Navbar />
                  <div className="app-body">
                    <Sidebar />
                    <main className="main-content">
                      <Routes>
                        <Route path="/" element={<HomePage />} />
                        <Route path="/explore" element={<HomePage />} />
                        <Route path="/content/:id" element={<ContentDetailPage />} />
                        <Route path="/creators" element={<CreatorsPage />} />
                        <Route path="/creator/:id" element={<CreatorDetailPage />} />
                        <Route path="/create-content" element={<CreateContentPage />} />
                        <Route path="/my-contents" element={<MyContentsPage />} />
                        <Route path="/subscriptions" element={<SubscriptionsPage />} />
                        <Route path="/following" element={<FollowingPage />} />
                        <Route path="/badges" element={<BadgesPage />} />
                        <Route path="/profile" element={<ProfilePage />} />
                        <Route path="*" element={<Navigate to="/" />} />
                      </Routes>
                    </main>
                  </div>
                </div>
              ) : (
                <Routes>
                  <Route path="/login" element={<LoginPage />} />
                  <Route path="/register" element={<RegisterPage />} />
                  <Route path="*" element={<Navigate to="/login" />} />
                </Routes>
              )
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
