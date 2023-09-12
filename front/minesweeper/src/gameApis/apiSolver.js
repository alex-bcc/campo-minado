import axios from "axios";

export const apiSolver = async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/solver-board');
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log('error', error);
    throw error;
  }
};

export default apiSolver;
