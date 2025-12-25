import { useNavigate } from 'react-router-dom';
import './CreatorCard.css';

function CreatorCard({ creator }) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/creator/${creator.id}`);
  };

  // Mock veriler
  const followers = 1250;
  const contentCount = 24;
  const isVerified = Math.random() > 0.5;

  return (
    <div className="creator-card" onClick={handleClick}>
      <div className="creator-avatar">
        <img 
          src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${creator.user?.username}`}
          alt={creator.user?.firstName}
        />
        {isVerified && <span className="verified-badge">✓</span>}
      </div>

      <div className="creator-info">
        <h3 className="creator-title">
          {creator.user?.firstName} {creator.user?.lastName}
        </h3>
        <p className="creator-username">@{creator.user?.username}</p>

        {creator.biography && (
          <p className="creator-bio">
            {creator.biography.substring(0, 60)}...
          </p>
        )}

        <div className="creator-stats">
          <div className="stat">
            <span className="stat-value">{contentCount}</span>
            <span className="stat-label">İçerik</span>
          </div>
          <div className="stat">
            <span className="stat-value">{followers}</span>
            <span className="stat-label">Takipçi</span>
          </div>
        </div>

        <button className="follow-btn">Takip Et</button>
      </div>
    </div>
  );
}

export default CreatorCard;
