import { NavLink } from 'react-router-dom'

export default function AuthNav() {
  return (
    <nav className="navbar">
      <NavLink to="/login">Kullanıcı Girişi</NavLink>
      <NavLink to="/admin/login">Admin Girişi</NavLink>
      <NavLink to="/register">Kayıt Ol</NavLink>
    </nav>
  )
}
