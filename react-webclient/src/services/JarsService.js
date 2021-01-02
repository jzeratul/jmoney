import axios from "axios";
import authHeader from './AuthHeader'

const API_URL = 'http://localhost/jmoney/api/v1/data';

// TODO check if we can generate a JS client from swagger, a client that's usable in reactjs.

// JARS
const getUserJars = () => {
  return axios.get(`${API_URL}/jars`, {headers: authHeader()});
}
const createUserJars = (jars) => {
  return axios.post(`${API_URL}/jars`, {headers: authHeader(), body: jars});
}
// INCOME
const getUserIncome = () => {
  return axios.get(`${API_URL}/income`, {headers: authHeader()});
}
const createUserIncome = (incomes) => {
  return axios.post(`${API_URL}/income`, {headers: authHeader(), body: incomes});
}
// PAYMENTS
const getJarPayments = () => {
  return axios.get(`${API_URL}/jars/payments`, { headers: authHeader() } ); // TODO add the encodedJarId on the header
}
const createJarPayments = (payments) => {
  return axios.post(`${API_URL}/jars/payments`, { headers: authHeader(), body: payments } ); // TODO add the encodedJarId on the header
}

const generateRandomData = () => {
  return axios.post(`${API_URL}/random-data-generator`, { headers: authHeader() } );
}

export default {
  getUserJars,
  getUserIncome,
  getJarPayments,
  createUserJars,
  createUserIncome,
  createJarPayments,
  generateRandomData
}