import styles from './buttons.module.scss';
import PropTypes from 'prop-types';
import { resetGame } from '../gameApis/gameApi';

const Buttons = ({ selected, generateGrid, resetTimer, fetchSolverBoard, fetchBoard, setShowSolverBoard }) => {
  console.log(selected);

  const handleResetGame = async () => {
    await resetGame();
    await fetchBoard();
    await fetchSolverBoard();
    setShowSolverBoard(false);
    generateGrid();  
    resetTimer();
    console.log('resetou ');
  };

  const handleSolver = async () => {
    try {
      await fetchBoard();
      await fetchSolverBoard();
      setShowSolverBoard(true);
      console.log("funciona ");
    } catch (error) {
      console.error('errouuu', error);
    }
  }

  return (
    <div className={`${styles.selectorContainer} py-4`} >
      <div 
        role='button' 
        className={`${styles.play} rounded-lg text-white`} 
        onClick={handleResetGame}
      >Novo Jogo</div>

      <div 
        role='button' 
        className={`${styles.normal} border-2 border-white rounded-lg text-white`} 
        onClick={handleSolver}
      >Gabarito</div>
    </div>
  )
}

Buttons.propTypes = {
  selected: PropTypes.number.isRequired,
  setSelected: PropTypes.func.isRequired,
  setStarted: PropTypes.func.isRequired,
  generateGrid: PropTypes.func,
  resetTimer: PropTypes.func,
  fetchSolverBoard: PropTypes.func.isRequired,
  fetchBoard: PropTypes.func.isRequired,
  setShowSolverBoard: PropTypes.func,
};

export default Buttons;
