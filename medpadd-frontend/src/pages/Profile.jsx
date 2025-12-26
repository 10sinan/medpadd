import { useAuthStore } from '../store/auth'

export default function Profile() {
  const user = useAuthStore((s) => s.user)
  const role = useAuthStore((s) => s.role)

  return (
    <div className="page-content">
      <div className="card">
        <h2 className="page-title">Profil</h2>
        
        <div style={{ display: 'grid', gap: 16 }}>
          <div style={{ padding: 16, background: '#f0f4f8', borderRadius: 8 }}>
            <h3>Hesap Bilgileri</h3>
            <div style={{ display: 'grid', gap: 8, marginTop: 12 }}>
              <div><strong>Ad:</strong> {user?.firstName}</div>
              <div><strong>Soyadı:</strong> {user?.lastName}</div>
              <div><strong>Kullanıcı Adı:</strong> {user?.username}</div>
              <div><strong>E-posta:</strong> {user?.email}</div>
              <div><strong>Rol:</strong> {role || user?.systemRoles?.roleName || '—'}</div>
              {user?.birthday && <div><strong>Doğum Tarihi:</strong> {user.birthday}</div>}
            </div>
          </div>

          <div style={{ padding: 16, background: '#fef3c7', borderRadius: 8, border: '1px solid #fcd34d' }}>
            <h4>Güvenlik Notu</h4>
            <p>Şifreleriniz güvende tutun ve başkasıyla paylaşmayın.</p>
          </div>
        </div>
      </div>
    </div>
  )
}
