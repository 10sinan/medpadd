import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { contentsService, commentsService, ratingsService } from '../services/apiService';
import CommentForm from '../components/CommentForm';
import RatingForm from '../components/RatingForm';
import './ContentDetailPage.css';

function ContentDetailPage() {
  const { id } = useParams();
  const [content, setContent] = useState(null);
  const [comments, setComments] = useState([]);
  const [ratings, setRatings] = useState([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    fetchContent();
    fetchComments();
    fetchRatings();
  }, [id]);

  const fetchContent = async () => {
    try {
      const response = await contentsService.getById(id);
      setContent(response.data);
    } catch (err) {
      console.error(err);
    } finally {
      setIsLoading(false);
    }
  };

  const fetchComments = async () => {
    try {
      const response = await commentsService.getByContent(id);
      setComments(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  const fetchRatings = async () => {
    try {
      const response = await ratingsService.getByContent(id);
      setRatings(response.data);
    } catch (err) {
      console.error(err);
    }
  };

  const avgRating = ratings.length > 0 
    ? (ratings.reduce((sum, r) => sum + r.rating, 0) / ratings.length).toFixed(1)
    : 0;

  if (isLoading) return <div className="loading">Yükleniyor...</div>;
  if (!content) return <div className="error">İçerik bulunamadı</div>;

  return (
    <div className="content-detail">
      <div className="content-detail-container">
        <div className="content-main">
          <div className="content-image-large">
            <img 
              src={`https://via.placeholder.com/600x400?text=${content.title}`}
              alt={content.title}
            />
          </div>

          <div className="content-header">
            <h1>{content.title}</h1>
            
            <div className="content-meta">
              <div className="creator-info">
                <img 
                  src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${content.contentCreator?.user?.username}`}
                  alt="Avatar"
                  className="creator-avatar-small"
                />
                <div>
                  <p className="creator-name">
                    {content.contentCreator?.user?.firstName} {content.contentCreator?.user?.lastName}
                  </p>
                  <p className="creator-username">@{content.contentCreator?.user?.username}</p>
                </div>
              </div>

              <div className="content-stats">
                <div className="stat">
                  <span className="stat-label">Puan</span>
                  <span className="stat-value">⭐ {avgRating}</span>
                </div>
                <div className="stat">
                  <span className="stat-label">Yorumlar</span>
                  <span className="stat-value">{comments.length}</span>
                </div>
              </div>
            </div>

            {content.price && (
              <div className="content-price-large">
                ${content.price.toFixed(2)}
                <button className="btn btn-primary">Satın Al</button>
              </div>
            )}
          </div>

          <div className="content-description">
            <h2>Hakkında</h2>
            <p>{content.contentCreator?.biography || 'Açıklama bulunmamaktadır.'}</p>
          </div>
        </div>

        <aside className="content-sidebar">
          <button className="btn btn-primary full-width">Takip Et</button>
          <button className="btn btn-secondary full-width">Paylaş</button>
          <button className="btn btn-secondary full-width">Şikayet Et</button>
        </aside>
      </div>

      <div className="content-interactions">
        <div className="content-interactions-container">
          <section className="interaction-section">
            <h2>⭐ Puan Ver</h2>
            <RatingForm contentId={id} onRated={fetchRatings} />
          </section>

          <section className="interaction-section">
            <h2>💬 Yorumlar ({comments.length})</h2>
            <CommentForm contentId={id} onCommented={fetchComments} />
            <div className="comments-list">
              {comments.map((comment) => (
                <div key={comment.id} className="comment">
                  <img 
                    src={`https://api.dicebear.com/7.x/avataaars/svg?seed=${comment.user?.username}`}
                    alt="Avatar"
                    className="comment-avatar"
                  />
                  <div className="comment-body">
                    <h4>{comment.user?.firstName} {comment.user?.lastName}</h4>
                    <p className="comment-username">@{comment.user?.username}</p>
                    <p className="comment-text">{comment.text}</p>
                  </div>
                </div>
              ))}
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}

export default ContentDetailPage;
