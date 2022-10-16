import axios from "axios";

export default axios.create({
  baseURL: "http://54.180.8.164:8000/api",
  headers: {
    "Content-type": "application/json"
  }
});