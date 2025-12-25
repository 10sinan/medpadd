import { useNavigate } from 'react-router-dom';
import './ContentCard.css';

function ContentCard({ content }) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/content/${content.id}`);
  };

  // Mock rating hesapla
  const avgRating = 4.5;
  const reviewCount = 12;

  return (
    <div className="content-card" onClick={handleClick}>
      <div className="content-image">
        <img 
          src={`https://via.placeholder.com/220x280?text=${content.title}`} 
          alt={content.title}
        />
        <div className="content-overlay">
          <button className="play-btn">▶</button>
        </div>
      </div>

      <div className="content-info">
        <h3 className="content-title">{content.title}</h3>
        
        <div className="content-creator">
          <span className="creator-name">
            {content.contentCreator?.user?.firstName}
          </span>
        </div>

        <div className="content-rating">
          <span className="stars">⭐ {avgRating}</span>
          <span className="review-count">({reviewCount})</span>
        </div>

        <div className="content-price">
          {content.price ? (
            <span className="price">${content.price.toFixed(2)}</span>
          ) : (
            <span className="free">Ücretsiz</span>
          )}
        </div>
      </div>
    </div>
  );
}

export default ContentCard;
