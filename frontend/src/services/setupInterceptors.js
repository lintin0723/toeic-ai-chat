import axios from "axios";

const setup = () => {
  axios.interceptors.request.use(
    (config) => {
      const user = JSON.parse(localStorage.getItem("user"));
      // THE FINAL, REAL FIX: Use 'accessToken' which is sent from the backend.
      if (user && user.accessToken) {
        config.headers.Authorization = "Bearer " + user.accessToken;
      }
      return config;
    },
    (error) => {
      return Promise.reject(error);
    }
  );
};

export default setup;
