import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import { contentCreatorsService, contentsService } from '../services/apiService';
import ContentCard from '../components/ContentCard';
import './MyContentsPage.css';

function MyContentsPage() {
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [contents, setContents] = useState([]);
  const [creator, setCreator] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [filterType, setFilterType] = useState('all');

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }
    fetchMyContents();
  }, [user, navigate]);

  const fetchMyContents = async () => {
    try {
      setIsLoading(true);

      // Get creator profile
      const creatorRes = await contentCreatorsService.getByUserId(user.id);
      if (creatorRes.data) {
        setCreator(creatorRes.data);
        // Get contents by creator
        const contentsRes = await contentsService.getByContentCreator(creatorRes.data.id);
        setContents(contentsRes.data || []);
      } else {
        setContents([]);
      }

      setError(null);
    } catch (err) {
      setError('İçerikleri yüklerken hata oluştu');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const handleDelete = async (contentId) => {
    if (window.confirm('Bu içeriği silmek istediğinize emin misiniz?')) {
      try {
        await contentsService.delete(contentId);
        setContents(contents.filter((c) => c.id !== contentId));
      } catch (err) {
        console.error('Silme hatası:', err);
        setError('İçerik silinirken hata oluştu');
      }
    }
  };

  const filteredContents = filterType === 'all'
    ? contents
    : contents.filter((c) => c.contentType?.toLowerCase() === filterType.toLowerCase());

  if (isLoading) {
    return (
      <div className="my-contents-page">
        <div className="loading">İçerikler yükleniyor...</div>
      </div>
    );
  }

  return (
    <div className="my-contents-page">
      <div className="page-header">
        <h1>Benim İçeriklerim</h1>
        <button
          className="create-button"
          onClick={() => navigate('/create-content')}
        >
          + Yeni İçerik
        </button>
      </div>

      {error && <div className="error-message">{error}</div>}

      {contents.length === 0 ? (
        <div className="no-contents">
          <h2>Henüz içerik oluşturmadınız</h2>
          <p>Sanat eserlerinizi paylaşmaya başlamak için yeni içerik ekleyin.</p>
          <button
            className="create-button large"
            onClick={() => navigate('/create-content')}
          >
            İlk İçeriğinizi Oluşturun
          </button>
        </div>
      ) : (
        <>
          <div className="filter-buttons">
            <button
              className={`filter-btn ${filterType === 'all' ? 'active' : ''}`}
              onClick={() => setFilterType('all')}
            >
              Tümü ({contents.length})
            </button>
            <button
              className={`filter-btn ${filterType === 'story' ? 'active' : ''}`}
              onClick={() => setFilterType('story')}
            >
              Hikaye
            </button>
            <button
              className={`filter-btn ${filterType === 'comic' ? 'active' : ''}`}
              onClick={() => setFilterType('comic')}
            >
              Çizgi Roman
            </button>
            <button
              className={`filter-btn ${filterType === 'painting' ? 'active' : ''}`}
              onClick={() => setFilterType('painting')}
            >
              Resim
            </button>
            <button
              className={`filter-btn ${filterType === 'poetry' ? 'active' : ''}`}
              onClick={() => setFilterType('poetry')}
            >
              Şiir
            </button>
          </div>

          {filteredContents.length === 0 ? (
            <div className="no-results">Bu kategoride içerik bulunamadı</div>
          ) : (
            <div className="contents-list">
              {filteredContents.map((content) => (
                <div key={content.id} className="content-item-card">
                  <div className="content-preview">
                    <img
                      src={content.coverImageUrl || `https://via.placeholder.com/200?text=${content.title}`}
                      alt={content.title}
                    />
                  </div>
                  <div className="content-details">
                    <h3>{content.title}</h3>
                    <p className="content-type">{content.contentType}</p>
                    <p className="content-description">{content.description?.substring(0, 100)}...</p>
                    <div className="content-meta">
                      <span className="price">
                        {content.price ? `₺${content.price}` : 'Ücretsiz'}
                      </span>
                      <span className="rating">⭐ {content.averageRating?.toFixed(1) || '0'}</span>
                    </div>
                    <div className="content-actions">
                      <button
                        className="edit-btn"
                        onClick={() => navigate(`/edit-content/${content.id}`)}
                      >
                        Düzenle
                      </button>
                      <button
                        className="delete-btn"
                        onClick={() => handleDelete(content.id)}
                      >
                        Sil
                      </button>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          )}
        </>
      )}
    </div>
  );
}

export default MyContentsPage;
