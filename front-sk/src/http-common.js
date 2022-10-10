import axios from "axios";

export default axios.create({
  // baseURL: "http://13.125.198.49:8078/api",
  baseURL: "http://localhost:8000/api",
  headers: {
    "Content-type": "application/json"
  }
});