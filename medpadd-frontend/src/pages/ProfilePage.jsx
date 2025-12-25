import { useState, useEffect } from 'react';
import { useAuthStore } from '../store/store';
import { usersService, contentCreatorsService, contentsService } from '../services/apiService';
import './ProfilePage.css';

function ProfilePage() {
  const { user } = useAuthStore();
  const [creator, setCreator] = useState(null);
  const [contents, setContents] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [editData, setEditData] = useState({
    firstName: user?.firstName || '',
    lastName: user?.lastName || '',
    biography: '',
  });

  useEffect(() => {
    if (user?.id) {
      fetchCreator();
      fetchContents();
    }
  }, [user?.id]);

  const fetchCreator = async () => {
    try {
      const response = await contentCreatorsService.getByUserId(user.id);
      if (response.data && response.data.length > 0) {
        setCreator(response.data[0]);
        setEditData((prev) => ({
          ...prev,
          biography: response.data[0].biography || '',
        }));
      }
    } catch (err) {
      console.error(err);
    }
  };

  const fetchContents = async () => {
    try {
      const allContents = await contentsService.getAll();
      const userContents = allContents.data.filter(
        (c) => c.contentCreator?.user?.id === user.id
      );
      setContents(userContents);
    } catch (err) {
      console.error(err);
    }
  };

  const handleSaveProfile = async () => {
    try {
      // Update user info
      await usersService.create({
        ...user,
        firstName: editData.firstName,
        lastName: editData.lastName,
      });

      // Update or create creator profile
      if (creator) {
        // Update existing
        await contentCreatorsService.create({
          ...creator,
          biography: editData.biography,
        });
      } else {
        // Create new
        await contentCreatorsService.create({
          user: { id: user.id },
          biography: editData.biography,
        });
      }

      setIsEditing(false);
      fetchCreator();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="profile-page">
      <div className="profile-header">
        <div className="profile-banner"></div>
        <div className="profile-container">
          <div className="profile-avatar">
            <img 
              src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${user?.username}`}
              alt="Avatar"
            />
          </div>

          <div className="profile-info">
            {isEditing ? (
              <>
                <input
                  type="text"
                  value={editData.firstName}
                  onChange={(e) =>
                    setEditData((prev) => ({
                      ...prev,
                      firstName: e.target.value,
                    }))
                  }
                  placeholder="Ad"
                />
                <input
                  type="text"
                  value={editData.lastName}
                  onChange={(e) =>
                    setEditData((prev) => ({
                      ...prev,
                      lastName: e.target.value,
                    }))
                  }
                  placeholder="Soyad"
                />
              </>
            ) : (
              <h1>
                {user?.firstName} {user?.lastName}
              </h1>
            )}

            <p className="profile-username">@{user?.username}</p>

            {isEditing ? (
              <textarea
                value={editData.biography}
                onChange={(e) =>
                  setEditData((prev) => ({
                    ...prev,
                    biography: e.target.value,
                  }))
                }
                placeholder="Biyografi"
                rows="3"
              />
            ) : (
              <p className="profile-bio">
                {creator?.biography || 'Henüz biyografi eklenmemiş'}
              </p>
            )}

            <div className="profile-stats">
              <div className="stat">
                <span className="stat-value">{contents.length}</span>
                <span className="stat-label">İçerik</span>
              </div>
              <div className="stat">
                <span className="stat-value">1.2K</span>
                <span className="stat-label">Takipçi</span>
              </div>
              <div className="stat">
                <span className="stat-value">45</span>
                <span className="stat-label">Takip Edilen</span>
              </div>
            </div>

            <div className="profile-actions">
              {isEditing ? (
                <>
                  <button 
                    className="btn btn-primary"
                    onClick={handleSaveProfile}
                  >
                    Kaydet
                  </button>
                  <button 
                    className="btn btn-secondary"
                    onClick={() => setIsEditing(false)}
                  >
                    İptal Et
                  </button>
                </>
              ) : (
                <button 
                  className="btn btn-primary"
                  onClick={() => setIsEditing(true)}
                >
                  Profili Düzenle
                </button>
              )}
            </div>
          </div>
        </div>
      </div>

      <div className="profile-content">
        <div className="content-section">
          <h2>📚 Yayınlanan İçerikler ({contents.length})</h2>
          
          {contents.length > 0 ? (
            <div className="contents-grid">
              {contents.map((content) => (
                <div key={content.id} className="content-item">
                  <div className="content-thumbnail">
                    <img 
                      src={`https://via.placeholder.com/200x150?text=${content.title}`}
                      alt={content.title}
                    />
                  </div>
                  <h3>{content.title}</h3>
                  <p>
                    {content.price ? `$${content.price}` : 'Ücretsiz'}
                  </p>
                </div>
              ))}
            </div>
          ) : (
            <div className="no-content">
              <p>Henüz içerik yayınlanmamış</p>
              <button className="btn btn-primary">İçerik Oluştur</button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default ProfilePage;
