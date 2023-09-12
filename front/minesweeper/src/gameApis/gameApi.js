import axios from 'axios';

export const resetGame = async () => {
  try {
    await axios.post('http://localhost:8081/api/reset');
  } catch (error) {
    console.error("Erro ao resetar o jogo", error);
  }
};

