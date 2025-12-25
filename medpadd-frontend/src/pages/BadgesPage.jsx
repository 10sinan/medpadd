import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuthStore } from '../store/store';
import './BadgesPage.css';

const placeholderBadges = [
    {
      id: 1,
      name: 'Yeni Başlayan',
      description: 'İlk içeriğinizi yayınlayın',
      icon: '🌱',
      earned: true,
      earnedDate: '2024-01-15',
    },
    {
      id: 2,
      name: 'Hikayelerin Efendisi',
      description: '10 hikaye yayınlayın',
      icon: '📖',
      earned: true,
      earnedDate: '2024-06-20',
    },
    {
      id: 3,
      name: 'Popüler Yazar',
      description: 'Bir içerikte 100 yorum alın',
      icon: '⭐',
      earned: false,
    },
    {
      id: 4,
      name: 'Sosyal Melez',
      description: '1000 takipçiye ulaşın',
      icon: '👥',
      earned: false,
    },
    {
      id: 5,
      name: 'Çizgi Roman Ustası',
      description: '5 çizgi roman yayınlayın',
      icon: '🎨',
      earned: false,
    },
    {
      id: 6,
      name: 'Şiir Şairi',
      description: '10 şiir yayınlayın',
      icon: '✨',
      earned: false,
    },
  ];

function BadgesPage() {
  const navigate = useNavigate();
  const { user } = useAuthStore();
  const [badges, setBadges] = useState([]);
  const [allBadges, setAllBadges] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    if (!user) {
      navigate('/login');
      return;
    }
    fetchBadges();
  }, [user, navigate]);

  const fetchBadges = async () => {
    try {
      setIsLoading(true);
      // TODO: Implement badge fetching when backend endpoint is ready
      // For now, use placeholder badges
      setAllBadges(placeholderBadges);
      setBadges(placeholderBadges.slice(0, 2)); // Simulating earned badges
    } catch (err) {
      console.error('Rozetler yüklenemedi:', err);
    } finally {
      setIsLoading(false);
    }
  };

  const unlockedCount = badges.length;
  const totalCount = allBadges.length;

  return (
    <div className="badges-page">
      <div className="badges-header">
        <h1>Rozetlerim</h1>
        <div className="badges-stats">
          <div className="stat">
            <span className="stat-number">{unlockedCount}</span>
            <span className="stat-label">Elde Edilen</span>
          </div>
          <div className="stat">
            <span className="stat-number">{totalCount}</span>
            <span className="stat-label">Toplam</span>
          </div>
          <div className="stat">
            <span className="stat-number">
              {Math.round((unlockedCount / totalCount) * 100)}%
            </span>
            <span className="stat-label">İlerleme</span>
          </div>
        </div>
      </div>

      <div className="progress-bar-container">
        <div className="progress-bar">
          <div
            className="progress-fill"
            style={{
              width: `${(unlockedCount / totalCount) * 100}%`,
            }}
          ></div>
        </div>
      </div>

      {isLoading ? (
        <div className="loading">Rozetler yükleniyor...</div>
      ) : (
        <>
          <div className="badges-section">
            <h2>Elde Edilen Rozetler</h2>
            {badges.length === 0 ? (
              <p className="no-badges">Henüz rozetiniz yok</p>
            ) : (
              <div className="badges-grid">
                {badges.map((badge) => (
                  <div key={badge.id} className="badge-card earned">
                    <div className="badge-icon">{badge.icon}</div>
                    <h3>{badge.name}</h3>
                    <p>{badge.description}</p>
                    {badge.earnedDate && (
                      <p className="earned-date">
                        {new Date(badge.earnedDate).toLocaleDateString('tr-TR')}
                      </p>
                    )}
                  </div>
                ))}
              </div>
            )}
          </div>

          <div className="badges-section">
            <h2>Kilitli Rozetler</h2>
            <div className="badges-grid">
              {allBadges
                .filter((b) => !b.earned)
                .map((badge) => (
                  <div key={badge.id} className="badge-card locked">
                    <div className="badge-icon locked-icon">🔒</div>
                    <h3>{badge.name}</h3>
                    <p>{badge.description}</p>
                  </div>
                ))}
            </div>
          </div>
        </>
      )}

      <div className="badges-info">
        <h3>Rozetler Hakkında</h3>
        <p>
          Rozetler, başarılarınızı kutlamak ve topluluğunuzu motivate etmek için
          tasarlanmıştır. İçerik oluşturdukça, paylaştıkça ve bağlantı
          kurdukça yeni rozetler açın!
        </p>
      </div>
    </div>
  );
}

export default BadgesPage;
