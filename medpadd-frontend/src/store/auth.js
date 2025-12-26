import { create } from "zustand";

export const useAuthStore = create((set) => ({
  user: null,
  role: null,
  login: (user) => set({ user, role: user?.systemRoles?.roleName ?? null }),
  logout: () => set({ user: null, role: null }),
}));
