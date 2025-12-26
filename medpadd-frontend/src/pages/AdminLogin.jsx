import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { getUserByUsername, getUserByEmail } from '../api/client'
import { useAuthStore } from '../store/auth'

export default function AdminLogin() {
  const [identifier, setIdentifier] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()
  const login = useAuthStore((s) => s.login)

  const onSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setLoading(true)
    try {
      let user = null
      try {
        user = await getUserByUsername(identifier)
      } catch {
        try {
          user = await getUserByEmail(identifier)
        } catch {
          /* not found */
          void 0
        }
      }
      if (!user) {
        setError('Kullanıcı bulunamadı.')
        return
      }
      if (user.password !== password) {
        setError('Şifre hatalı.')
        return
      }
      // Backend artık systemRoles'u direkt döndürüyor
      const roleName = user?.systemRoles?.roleName || ''
      if (roleName.toUpperCase() !== 'ADMIN') {
        setError('Admin yetkisi bulunmuyor.')
        return
      }
      login(user)
      navigate('/dashboard')
    } catch {
      setError('Giriş sırasında bir hata oluştu.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="card" style={{ maxWidth: 520 }}>
      <h2 className="page-title">Admin Girişi</h2>
        <form onSubmit={onSubmit} className="form">
          <label>
            Kullanıcı adı veya E-posta
            <input
              className="input"
              type="text"
              value={identifier}
              onChange={(e) => setIdentifier(e.target.value)}
              required
            />
          </label>
          <label>
            Şifre
            <input
              className="input"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>
          {error && <div className="error">{error}</div>}
          <div className="actions">
            <button className="btn btn-primary" type="submit" disabled={loading}>
              {loading ? 'Giriş yapılıyor…' : 'Giriş Yap'}
            </button>
          </div>
        </form>
      </div>
    )
  }
