// import { useState, useEffect } from "react"
// import { apiSolver } from "../gameApis/apiSolver"
import PropTypes from 'prop-types';

export const SolverBoard = ({ data }) => {
  return (
    <div className="mx-auto w-fit text-white py-4">
      {data && data.length > 0 ? (
        data.map((row, rowIndex) => (
          <div key={rowIndex}>
            {row.map((cell, cellIndex) => (
              <span key={cellIndex}>
                
                {cell.isMine ? '💣' : cell.adjacentMines}
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
  data: PropTypes.arrayOf(PropTypes.arrayOf(PropTypes.object)),
};

export default SolverBoard;