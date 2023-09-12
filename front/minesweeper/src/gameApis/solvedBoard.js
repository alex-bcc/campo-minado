import axios from 'axios'

export const solvedBoard = async () => {
  try {
    const response = await axios.get('http://localhost:8081/api/solver-board');
    return response.data;
  } catch (error) {
    console.error('Erro ao buscar o tabuleiro resolvido', error);
  }
};

export default solvedBoard