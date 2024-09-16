import axios from 'axios';
import { getBasicAuth } from "./AuthApiService";
const API_BASE_URL = 'http://localhost:8080/api/v1/users';


axios.interceptors.response.use(
    response => response,
    error => {
      
      console.error('API request error:', error);
      return Promise.reject(error);
    }
    
  );
export const findAllUsers = () =>
    axios.get(`${API_BASE_URL}/all`, {
      headers: {
        'Authorization': getBasicAuth()
      }
  });