import { useAuthStore } from '../store/auth'

export default function AdminSuccess() {
  const user = useAuthStore((s) => s.user)
  const role = useAuthStore((s) => s.role)
  return (
    <div className="card" style={{ maxWidth: 640 }}>
      <h2 className="page-title">Başardınız!! 🎉</h2>
        <p style={{ marginBottom: 12 }}>Admin girişi başarılı.</p>
        {user && (
          <div style={{ display: 'grid', gap: 8 }}>
            <div><strong>Kullanıcı Adı:</strong> {user.username}</div>
            <div><strong>E-posta:</strong> {user.email}</div>
            <div><strong>Rol:</strong> {role || user?.systemRoles?.roleName || '—'}</div>
          </div>
        )}
      </div>
    )
  }
