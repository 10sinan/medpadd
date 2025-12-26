import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { createUser, existsByUsername, existsByEmail } from '../api/client'

export default function Register() {
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [username, setUsername] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [confirm, setConfirm] = useState('')
  const [error, setError] = useState('')
  const [info, setInfo] = useState('')
  const [loading, setLoading] = useState(false)
  const navigate = useNavigate()

  const onSubmit = async (e) => {
    e.preventDefault()
    setError('')
    setInfo('')
    if (password !== confirm) {
      setError('Şifreler eşleşmiyor.')
      return
    }
    setLoading(true)
    try {
      const [uExists, eExists] = await Promise.all([
        existsByUsername(username),
        existsByEmail(email),
      ])
      if (uExists) {
        setError('Kullanıcı adı kullanımda.')
        return
      }
      if (eExists) {
        setError('E-posta kullanımda.')
        return
      }
      // Backend otomatik olarak "Kullanıcı" rolünü atıyor
      const payload = {
        firstName,
        lastName,
        username,
        email,
        password,
      }
      const created = await createUser(payload)
      if (created?.id) {
        setInfo('Kayıt başarılı. Giriş sayfasına yönlendiriliyorsunuz…')
        setTimeout(() => navigate('/login'), 1200)
      } else {
        setError('Kayıt başarısız.')
      }
    } catch {
      setError('Kayıt sırasında bir hata oluştu.')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="card" style={{ maxWidth: 640 }}>
      <h2 className="page-title">Kayıt Ol</h2>
        <form onSubmit={onSubmit} className="form">
          <label>
            Ad
            <input className="input" type="text" value={firstName} onChange={(e) => setFirstName(e.target.value)} required />
          </label>
          <label>
            Soyad
            <input className="input" type="text" value={lastName} onChange={(e) => setLastName(e.target.value)} required />
          </label>
          <label>
            Kullanıcı Adı
            <input className="input" type="text" value={username} onChange={(e) => setUsername(e.target.value)} required />
          </label>
          <label>
            E-posta
            <input className="input" type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
          </label>
          <label>
            Şifre
            <input className="input" type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
          </label>
          <label>
            Şifre (Tekrar)
            <input className="input" type="password" value={confirm} onChange={(e) => setConfirm(e.target.value)} required />
          </label>
          {error && <div className="error">{error}</div>}
          {info && <div className="success">{info}</div>}
          <div className="actions">
            <button className="btn btn-primary" type="submit" disabled={loading}>
              {loading ? 'Kaydediliyor…' : 'Kayıt Ol'}
            </button>
          </div>
        </form>
      </div>
    )
  }
