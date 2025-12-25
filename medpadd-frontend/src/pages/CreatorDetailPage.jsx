import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { contentCreatorsService, contentsService, followRelationsService } from '../services/apiService';
import { useAuthStore } from '../store/store';
import ContentCard from '../components/ContentCard';
import './CreatorDetailPage.css';

function CreatorDetailPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [creator, setCreator] = useState(null);
  const [contents, setContents] = useState([]);
  const [isFollowing, setIsFollowing] = useState(false);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchCreatorData();
  }, [id]);

  const fetchCreatorData = async () => {
    try {
      setIsLoading(true);
      const creatorRes = await contentCreatorsService.getById(id);
      setCreator(creatorRes.data);

      const contentsRes = await contentsService.getByContentCreator(id);
      setContents(contentsRes.data);

      setError(null);
    } catch (err) {
      setError('Creator verisi yüklenemedi');
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const handleFollow = async () => {
    if (!user) {
      navigate('/login');
      return;
    }

    try {
      const followData = {
        followerId: user.id,
        followingId: creator.userId,
      };

      if (isFollowing) {
        // TODO: Unfollow işlemi
        setIsFollowing(false);
      } else {
        await followRelationsService.create(followData);
        setIsFollowing(true);
      }
    } catch (err) {
      console.error('Follow hatası:', err);
    }
  };

  if (isLoading) {
    return (
      <div className="creator-detail-page">
        <div className="loading">Creator bilgisi yükleniyor...</div>
      </div>
    );
  }

  if (error || !creator) {
    return (
      <div className="creator-detail-page">
        <div className="error">{error || 'Creator bulunamadı'}</div>
      </div>
    );
  }

  return (
    <div className="creator-detail-page">
      <div className="creator-header">
        <div className="creator-banner">
          <img
            src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${creator.id}`}
            alt={creator.firstName}
            className="creator-avatar-large"
          />
        </div>

        <div className="creator-info-section">
          <div className="creator-info">
            <h1>
              {creator.firstName} {creator.lastName}
              {creator.verificationBadge && <span className="verified-badge">✓</span>}
            </h1>
            <p className="creator-bio">{creator.biography || 'Biyografi yok'}</p>

            <div className="creator-stats">
              <div className="stat">
                <span className="stat-number">{contents.length}</span>
                <span className="stat-label">İçerik</span>
              </div>
              <div className="stat">
                <span className="stat-number">0</span>
                <span className="stat-label">Takipçi</span>
              </div>
              <div className="stat">
                <span className="stat-number">0</span>
                <span className="stat-label">Takip Edilen</span>
              </div>
            </div>

            {user && user.id !== creator.userId && (
              <button
                className={`follow-button ${isFollowing ? 'following' : ''}`}
                onClick={handleFollow}
              >
                {isFollowing ? 'Takibi Bırak' : 'Takip Et'}
              </button>
            )}
          </div>
        </div>
      </div>

      <div className="creator-contents">
        <h2>Yayınlanan İçerik</h2>
        
        {contents.length === 0 ? (
          <div className="no-contents">Bu creator tarafından henüz içerik yayınlanmamış</div>
        ) : (
          <div className="content-grid">
            {contents.map((content) => (
              <ContentCard key={content.id} content={content} />
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

export default CreatorDetailPage;
