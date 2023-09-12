import PropTypes from 'prop-types';
import style from './modalGameOver.module.scss';

const ModalGameOver = ({ resetGame, generateGrid, fetchSolverBoard, fetchBoard, setShowSolverBoard }) => {
  const handleResetGame = async () => {
    await resetGame();
    // await fetchBoard();
    // () => setStarted(true) 
    setShowSolverBoard(false);
    await fetchSolverBoard();
    generateGrid(); 
  };

  return (
    <div className={`${style.modal}`}>
      <div className={`${style.modalContent}`} >
        <h2 className='text-2xl font-bold text-red-600' >Você perdeu!</h2>
        <button className='bg-amber-500 hover:bg-amber-700 duration-300 text-white font-bold py-2 px-4 rounded' onClick={handleResetGame} >Novo Jogo</button>
      </div>
    </div>
  )
}

ModalGameOver.propTypes = {
  resetGame: PropTypes.func.isRequired,
  generateGrid: PropTypes.func,
  setStarted: PropTypes.func.isRequired,
  fetchSolverBoard: PropTypes.func.isRequired,
  fetchBoard: PropTypes.func,
  setShowSolverBoard: PropTypes.func,
}

export default ModalGameOver
