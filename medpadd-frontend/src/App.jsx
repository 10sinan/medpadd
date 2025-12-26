import './App.css';
import { Routes, Route, useLocation } from 'react-router-dom'
import Login from './pages/Login.jsx'
import AdminLogin from './pages/AdminLogin.jsx'
import Register from './pages/Register.jsx'
import Success from './pages/Success.jsx'
import AdminSuccess from './pages/AdminSuccess.jsx'
import Home from './pages/Home.jsx'
import Explore from './pages/Explore.jsx'
import Profile from './pages/Profile.jsx'
import DashboardLayout from './components/DashboardLayout.jsx'
import AuthNav from './components/AuthNav.jsx'

function App() {
	const location = useLocation()
	
	// Show auth navbar only on login/register pages
	const showAuthNav = ['/login', '/admin/login', '/register'].includes(location.pathname)
	
	return (
		<div className="app">
			{showAuthNav && <AuthNav />}
			<main className={showAuthNav ? 'auth-shell' : ''}>
				<Routes>
					<Route path="/login" element={<Login />} />
					<Route path="/admin/login" element={<AdminLogin />} />
					<Route path="/register" element={<Register />} />
					<Route path="/success" element={<Success />} />
					<Route path="/admin/success" element={<AdminSuccess />} />
					
					<Route
						path="/dashboard"
						element={
							<DashboardLayout>
								<Home />
							</DashboardLayout>
						}
					/>
					<Route
						path="/dashboard/explore"
						element={
							<DashboardLayout>
								<Explore />
							</DashboardLayout>
						}
					/>
					<Route
						path="/dashboard/profile"
						element={
							<DashboardLayout>
								<Profile />
							</DashboardLayout>
						}
					/>
					
					<Route path="*" element={<Login />} />
				</Routes>
			</main>
		</div>
	)
}

export default App
