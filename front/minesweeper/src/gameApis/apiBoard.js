import axios from "axios";

export const apiBoard = async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/board');
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log('error ao achar o board', error);
    throw error;
  }
};

export default apiBoard;
