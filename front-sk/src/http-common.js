import axios from "axios";

export default axios.create({
  baseURL: "http://54.180.98.152:8000/api",
  headers: {
    "Content-type": "application/json"
  }
});