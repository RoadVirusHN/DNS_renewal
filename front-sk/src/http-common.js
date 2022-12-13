import axios from "axios";

export default axios.create({
  baseURL: `http://${process.env.VUE_APP_BACKEND}:8000/api`,
  headers: {
    "Content-type": "application/json"
  }
});
