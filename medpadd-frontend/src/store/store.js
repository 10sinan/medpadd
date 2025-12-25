import { create } from "zustand";

// Auth Store
export const useAuthStore = create((set) => ({
  user: localStorage.getItem("user")
    ? JSON.parse(localStorage.getItem("user"))
    : null,
  token: localStorage.getItem("authToken") || null,
  isLoading: false,
  error: null,

  setUser: (user) => {
    if (user) {
      localStorage.setItem("user", JSON.stringify(user));
    } else {
      localStorage.removeItem("user");
    }
    set({ user });
  },

  setToken: (token) => {
    if (token) {
      localStorage.setItem("authToken", token);
    } else {
      localStorage.removeItem("authToken");
    }
    set({ token });
  },

  logout: () => {
    localStorage.removeItem("user");
    localStorage.removeItem("authToken");
    set({ user: null, token: null });
  },

  setLoading: (isLoading) => set({ isLoading }),
  setError: (error) => set({ error }),
  clearError: () => set({ error: null }),
}));

// Content Store
export const useContentStore = create((set) => ({
  contents: [],
  selectedContent: null,
  isLoading: false,
  error: null,

  setContents: (contents) => set({ contents }),
  setSelectedContent: (content) => set({ selectedContent: content }),
  addContent: (content) =>
    set((state) => ({ contents: [content, ...state.contents] })),
  removeContent: (id) =>
    set((state) => ({
      contents: state.contents.filter((c) => c.id !== id),
    })),
  updateContent: (updatedContent) =>
    set((state) => ({
      contents: state.contents.map((c) =>
        c.id === updatedContent.id ? updatedContent : c
      ),
    })),

  setLoading: (isLoading) => set({ isLoading }),
  setError: (error) => set({ error }),
  clearError: () => set({ error: null }),
}));

// Creators Store
export const useCreatorsStore = create((set) => ({
  creators: [],
  selectedCreator: null,
  isLoading: false,
  error: null,

  setCreators: (creators) => set({ creators }),
  setSelectedCreator: (creator) => set({ selectedCreator: creator }),
  addCreator: (creator) =>
    set((state) => ({ creators: [creator, ...state.creators] })),
  removeCreator: (id) =>
    set((state) => ({
      creators: state.creators.filter((c) => c.id !== id),
    })),

  setLoading: (isLoading) => set({ isLoading }),
  setError: (error) => set({ error }),
  clearError: () => set({ error: null }),
}));

// UI Store
export const useUIStore = create((set) => ({
  sidebarOpen: true,
  darkMode: localStorage.getItem("darkMode") === "true",
  notifications: [],

  toggleSidebar: () => set((state) => ({ sidebarOpen: !state.sidebarOpen })),
  toggleDarkMode: () =>
    set((state) => {
      const newDarkMode = !state.darkMode;
      localStorage.setItem("darkMode", newDarkMode);
      return { darkMode: newDarkMode };
    }),
  addNotification: (notification) =>
    set((state) => ({
      notifications: [
        ...state.notifications,
        { id: Date.now(), ...notification },
      ],
    })),
  removeNotification: (id) =>
    set((state) => ({
      notifications: state.notifications.filter((n) => n.id !== id),
    })),
}));
