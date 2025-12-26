export default function Explore() {
  return (
    <div className="page-content">
      <div className="card">
        <h2 className="page-title">Keşfet</h2>
        <p>Burada farklı içerikler ve kullanıcılar bulabileceksiniz.</p>
        
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(200px, 1fr))', gap: 16, marginTop: 16 }}>
          {[1, 2, 3, 4, 5, 6].map((i) => (
            <div key={i} style={{ background: '#f0f4f8', padding: 16, borderRadius: 8, cursor: 'pointer' }}>
              <div style={{ fontSize: 32, marginBottom: 8 }}>
                {i % 3 === 0 ? '📖' : i % 3 === 1 ? '🎨' : '✍️'}
              </div>
              <h4>İçerik {i}</h4>
              <p style={{ fontSize: 12, color: '#666' }}>Başına bir göz atın</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}
