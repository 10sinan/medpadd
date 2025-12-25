import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import CreatorCard from '../components/CreatorCard';
import './FollowingPage.css';

function FollowingPage() {
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [following, setFollowing] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }
    fetchFollowing();
  }, [user, navigate]);

  const fetchFollowing = async () => {
    try {
      setIsLoading(true);
      // TODO: Implement following fetch when backend endpoint is ready
      // For now, show placeholder
      setFollowing([]);
      setError(null);
    } catch (err) {
      setError('Takip edilen kullanıcılar yüklenemedi');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const handleUnfollow = async (creatorId) => {
    if (window.confirm('Bu creator\'ı takibi kaldırmak istediğinize emin misiniz?')) {
      try {
        // TODO: Implement unfollow when backend endpoint is ready
        setFollowing(following.filter((f) => f.id !== creatorId));
      } catch (err) {
        console.error('Takip etme kaldırma hatası:', err);
        setError('Takip etme kaldırılırken hata oluştu');
      }
    }
  };

  const filteredFollowing = following.filter(
    (creator) =>
      creator.firstName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      creator.lastName?.toLowerCase().includes(searchTerm.toLowerCase()) ||
      creator.username?.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="following-page">
      <div className="following-header">
        <h1>Takip Ettiğim Creatorlar</h1>
        <p>{following.length} creator\'ı takip ediyorsunuz</p>
      </div>

      {error && <div className="error-message">{error}</div>}

      {following.length > 0 && (
        <div className="search-box">
          <input
            type="text"
            placeholder="Creator ara..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
      )}

      {isLoading ? (
        <div className="loading">Takip edilen creatorlar yükleniyor...</div>
      ) : following.length === 0 ? (
        <div className="no-following">
          <h2>Henüz hiç creator\'ı takip etmiyorsunuz</h2>
          <p>Favori creatorları takip ederek onların yeni eserlerinden haberdar olun</p>
          <button
            className="explore-button"
            onClick={() => navigate('/creators')}
          >
            Creatorları Keşfet
          </button>
        </div>
      ) : filteredFollowing.length === 0 ? (
        <div className="no-results">Arama sonucu bulunamadı</div>
      ) : (
        <div className="creators-grid">
          {filteredFollowing.map((creator) => (
            <div key={creator.id} className="creator-wrapper">
              <CreatorCard creator={creator} />
              <button
                className="unfollow-button"
                onClick={() => handleUnfollow(creator.id)}
              >
                Takip Bırak
              </button>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default FollowingPage;
