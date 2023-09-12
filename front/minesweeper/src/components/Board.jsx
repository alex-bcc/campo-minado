import PropTypes from 'prop-types';
import style from './board.module.scss';
import axios from 'axios';
import svgMine from '../assets/total-mines.svg';
import watch from '../assets/watch.svg';
import initialEmoji from '../assets/initial-emoji.svg';
import { useEffect, useState } from 'react';
import { resetGame } from '../gameApis/gameApi';

const Board = ({boardData, updateBoardData, data, setGrid, onGameOver, timeElapsed, setStarted, started}) => {
  const [totalMines, setTotalMines] = useState(null);
  const [revealedData, setRevealedData] = useState(boardData);



  const mineColors = {
    1: "#9d0208",
    2: "#3a0ca3",
    3: "#b5179e",
    4: "#f72585",
  }

  
  
  
  const handleCellClick = async (x, y) => {
    if (!started) {
      setStarted(true);
    }
    
    try {
      const response = await axios.post(`http://localhost:8081/api/reveal?x=${x}&y=${y}`);
      updateBoardData(response.data);
      console.log('coordenada revelada');
      
      if(response.data[x][y].isMine) {
        onGameOver(true);
        console.log("mina!!!");
      }
      // } else {
      //   let newData = [...revealedData];
      //   newData[x][y].isRevealed = true;
      //   setRevealedData(newData);
      // }
    } catch (error) {
      console.error("Erro ao revelar célula", error);
    }
  };



  useEffect(() => {
    const fetchGameInfo = async () => {
      try {
        const response = await axios.get('http://localhost:8081/api/game-info');
        setTotalMines(response.data.totalMines);
      } catch (error) {
        console.error('Erro ao buscar informações do jogo', error);
      }
    };
    
    fetchGameInfo();
  }, [boardData]);

  
  
  
  return (
    <div className={`${style.gridContainer} flex py-8`} >
      <div className='border-2 rounded-lg border-white' >
        <div className={`${style.upper} flex justify-between border`}>
            <span className={`${style.timer} flex gap-1.5 items-center`}>
              <p className='text-lg' >{totalMines !== null ? totalMines : '...'}</p>
              <img src={svgMine}/>
            </span>
            <span>
              {/* <img src={initialEmoji}/> */}
            </span>
            <span className='flex gap-1.5 items-center'>
              <p className='text-lg' >{timeElapsed}</p>
              <img src={watch}/>
            </span>
          </div>


        <div className={`${style.board}`} >
          {boardData ? (
            boardData.map((row, rowIndex) => (
              <div key={rowIndex} className={`${style.row}`}>
                {row.map((cell, cellIndex) => (
                  <div
                    key={cellIndex}
                    className=
                    {`
                      ${style.cell}  
                      ${cell.isRevealed? style.cellRevealed : ''}
                      `}
                      onClick={() => handleCellClick(rowIndex, cellIndex)}
                      style={cell.isRevealed && !cell.isMine ? { color: mineColors[cell.adjacentMines]} : {}}
                  >
                    {cell.isRevealed && (cell.isMine ? '💣' : cell.adjacentMines ? cell.adjacentMines : '')}
                  </div>
                ))}
              </div>
            ))
          ) : (
            <div>Loading board</div>
          )}
        </div>
      </div>
    </div>

  );
}




Board.propTypes = {
  data: PropTypes.array,
  boardData: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.object)),
  setGrid: PropTypes.func.isRequired,
  onGameOver: PropTypes.func.isRequired,
  timeElapsed: PropTypes.number,
  setStarted: PropTypes.func.isRequired, 
  started: PropTypes.bool.isRequired,
  updateBoardData: PropTypes.func.isRequired, 
};

export default Board