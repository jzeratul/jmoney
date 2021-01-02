import axios from "axios";
import authHeader from "./AuthHeader";

const API_URL = "http://localhost/jmoney/api/test/";

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
};

export default {
  getUserBoard,
};
