import { useAuthStore } from '../store/auth'

export default function Home() {
  const user = useAuthStore((s) => s.user)
  const role = useAuthStore((s) => s.role)

  return (
    <div className="page-content">
      <div className="card">
        <h2 className="page-title">Ana Sayfa</h2>
        <p>Hoş geldin, <strong>{user?.firstName} {user?.lastName}</strong>!</p>
        
        <div style={{ marginTop: 20, padding: 16, background: '#f0f4f8', borderRadius: 8 }}>
          <h3>Profil Bilgileri</h3>
          <div style={{ display: 'grid', gap: 8, marginTop: 12 }}>
            <div><strong>Kullanıcı Adı:</strong> {user?.username}</div>
            <div><strong>E-posta:</strong> {user?.email}</div>
            <div><strong>Rol:</strong> {role || user?.systemRoles?.roleName || '—'}</div>
            {user?.birthday && <div><strong>Doğum Tarihi:</strong> {user.birthday}</div>}
          </div>
        </div>

        <div style={{ marginTop: 20 }}>
          <h3>Hoş Geldin 👋</h3>
          <p>MedPadd platformuna hoş geldiniz. Keşfet sekmesinde içerik görebilir, Profil sekmesinde hesap ayarlarınızı yönetebilirsiniz.</p>
        </div>
      </div>
    </div>
  )
}
