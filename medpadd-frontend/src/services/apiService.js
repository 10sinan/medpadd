import apiClient from "../config/api";

const USERS_API = "/users";
const CONTENT_CREATORS_API = "/content-creators";
const CONTENTS_API = "/contents";
const COMMENTS_API = "/comments";
const RATINGS_API = "/ratings";
const SUBSCRIPTIONS_API = "/subscriptions";
const BADGES_API = "/badges";
const MEDIA_API = "/media";
const FOLLOW_RELATIONS_API = "/follow-relations";

// ============ Users Service ============
export const usersService = {
  create: (user) => apiClient.post(USERS_API, user),
  getById: (id) => apiClient.get(`${USERS_API}/${id}`),
  getAll: () => apiClient.get(USERS_API),
  delete: (id) => apiClient.delete(`${USERS_API}/${id}`),
  getByUsername: (username) =>
    apiClient.get(`${USERS_API}/by-username/${username}`),
  getByEmail: (email) => apiClient.get(`${USERS_API}/by-email/${email}`),
  existsByUsername: (username) =>
    apiClient.get(`${USERS_API}/exists/by-username/${username}`),
  existsByEmail: (email) =>
    apiClient.get(`${USERS_API}/exists/by-email/${email}`),
};

// ============ Content Creators Service ============
export const contentCreatorsService = {
  create: (creator) => apiClient.post(CONTENT_CREATORS_API, creator),
  getById: (id) => apiClient.get(`${CONTENT_CREATORS_API}/${id}`),
  getAll: () => apiClient.get(CONTENT_CREATORS_API),
  delete: (id) => apiClient.delete(`${CONTENT_CREATORS_API}/${id}`),
  getByUserId: (userId) =>
    apiClient.get(`${CONTENT_CREATORS_API}/by-user/${userId}`),
};

// ============ Contents Service ============
export const contentsService = {
  create: (content) => apiClient.post(CONTENTS_API, content),
  getById: (id) => apiClient.get(`${CONTENTS_API}/${id}`),
  getAll: () => apiClient.get(CONTENTS_API),
  delete: (id) => apiClient.delete(`${CONTENTS_API}/${id}`),
  getByContentCreator: (creatorId) =>
    apiClient.get(`${CONTENTS_API}/by-content-creator/${creatorId}`),
};

// ============ Comments Service ============
export const commentsService = {
  create: (comment) => apiClient.post(COMMENTS_API, comment),
  getById: (id) => apiClient.get(`${COMMENTS_API}/${id}`),
  getAll: () => apiClient.get(COMMENTS_API),
  delete: (id) => apiClient.delete(`${COMMENTS_API}/${id}`),
  getByContent: (contentId) =>
    apiClient.get(`${COMMENTS_API}/by-content/${contentId}`),
  getByUser: (userId) => apiClient.get(`${COMMENTS_API}/by-user/${userId}`),
};

// ============ Ratings Service ============
export const ratingsService = {
  create: (rating) => apiClient.post(RATINGS_API, rating),
  getById: (id) => apiClient.get(`${RATINGS_API}/${id}`),
  getAll: () => apiClient.get(RATINGS_API),
  delete: (id) => apiClient.delete(`${RATINGS_API}/${id}`),
  getByContent: (contentId) =>
    apiClient.get(`${RATINGS_API}/by-content/${contentId}`),
  getByUser: (userId) => apiClient.get(`${RATINGS_API}/by-user/${userId}`),
  getByUserAndContent: (userId, contentId) =>
    apiClient.get(`${RATINGS_API}/by-user/${userId}/content/${contentId}`),
};

// ============ Subscriptions Service ============
export const subscriptionsService = {
  create: (subscription) => apiClient.post(SUBSCRIPTIONS_API, subscription),
  getById: (id) => apiClient.get(`${SUBSCRIPTIONS_API}/${id}`),
  getAll: () => apiClient.get(SUBSCRIPTIONS_API),
  delete: (id) => apiClient.delete(`${SUBSCRIPTIONS_API}/${id}`),
  getByName: (name) => apiClient.get(`${SUBSCRIPTIONS_API}/by-name/${name}`),
};

// ============ Badges Service ============
export const badgesService = {
  create: (badge) => apiClient.post(BADGES_API, badge),
  getById: (id) => apiClient.get(`${BADGES_API}/${id}`),
  getAll: () => apiClient.get(BADGES_API),
  delete: (id) => apiClient.delete(`${BADGES_API}/${id}`),
  getByCode: (code) => apiClient.get(`${BADGES_API}/by-code/${code}`),
};

// ============ Media Service ============
export const mediaService = {
  create: (media) => apiClient.post(MEDIA_API, media),
  getById: (id) => apiClient.get(`${MEDIA_API}/${id}`),
  getAll: () => apiClient.get(MEDIA_API),
  delete: (id) => apiClient.delete(`${MEDIA_API}/${id}`),
  getByUser: (userId) => apiClient.get(`${MEDIA_API}/by-user/${userId}`),
};

// ============ Follow Relations Service ============
export const followRelationsService = {
  create: (follow) => apiClient.post(FOLLOW_RELATIONS_API, follow),
  getById: (id) => apiClient.get(`${FOLLOW_RELATIONS_API}/${id}`),
  getAll: () => apiClient.get(FOLLOW_RELATIONS_API),
  delete: (id) => apiClient.delete(`${FOLLOW_RELATIONS_API}/${id}`),
  getByContentCreator: (creatorId) =>
    apiClient.get(`${FOLLOW_RELATIONS_API}/by-content-creator/${creatorId}`),
  getByRole: (roleId) =>
    apiClient.get(`${FOLLOW_RELATIONS_API}/by-role/${roleId}`),
};
