import axios from "axios";

export default axios.create({
  baseURL: "http://13.125.223.239:8000/api",
  headers: {
    "Content-type": "application/json"
  }
});