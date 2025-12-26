import axios from "axios";

export const api = axios.create({
  baseURL: "/api",
  withCredentials: true,
});

export async function getRoleByName(roleName) {
  const res = await api.get(
    `/system-roles/by-name/${encodeURIComponent(roleName)}`
  );
  return res.data;
}

export async function createUser(user) {
  const res = await api.post("/users", user);
  return res.data;
}

export async function getUserByUsername(username) {
  const res = await api.get(
    `/users/by-username/${encodeURIComponent(username)}`
  );
  return res.data;
}

export async function getUserByEmail(email) {
  const res = await api.get(`/users/by-email/${encodeURIComponent(email)}`);
  return res.data;
}

export async function existsByUsername(username) {
  const res = await api.get(
    `/users/exists/by-username/${encodeURIComponent(username)}`
  );
  return res.data;
}

export async function existsByEmail(email) {
  const res = await api.get(
    `/users/exists/by-email/${encodeURIComponent(email)}`
  );
  return res.data;
}
