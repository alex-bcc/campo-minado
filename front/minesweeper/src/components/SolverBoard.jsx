// import { useState, useEffect } from "react"
// import { apiSolver } from "../gameApis/apiSolver"
import PropTypes from 'prop-types';
import style from './solverBoard.module.scss';
import close from '../assets/close-thick.svg';


export const SolverBoard = ({ solverBoard, setShowSolverBoard }) => {
  return (
    <div className={`${style.board} mx-auto w-fit text-white`}>
      <div className={`${style.upper}`}>
        <p className='py-2 text-lg ml-20' >Solver do Campo Minado</p>
        <img 
          className={`${style.close} mr-2`} 
          src={close} 
          alt=""
          onClick={() => setShowSolverBoard(false)} 
        />
      </div>

      {solverBoard && solverBoard.length > 0 ? (
        solverBoard.map((row, rowIndex) => (
          <div key={rowIndex} className={`${style.rows}`} >
            {row.map((cell, cellIndex) => (
              <span key={cellIndex} className={`${style.cell}`} >
                
                {cell.isFlagged ? '💣' : cell.adjacentMines}
              </span>
            ))}
          </div>
        ))
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};



SolverBoard.propTypes = {
  solverBoard: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.object)),
  setShowSolverBoard: PropTypes.func.isRequired, 
};

export default SolverBoard;