import { useEffect, useState, useRef } from 'react';
import './App.css';
import Buttons from './components/Buttons';
import Board from './components/Board';
import axios from 'axios';
import ModalGameOver from './components/ModalGameOver';
import { resetGame } from './gameApis/gameApi';
import SolverBoard from './components/SolverBoard';
import { apiSolver } from './gameApis/apiSolver';
// import Teste from './components/Teste';
import apiBoard from './gameApis/apiBoard';
// import Navbar from './components/Navbar';

const App = () => {
  const [difficulty, setDifficulty] = useState(0);
  const [started, setStarted] = useState(false);
  const [grid, setGrid] = useState(null);
  const [gameOver, setGameOver] = useState(false);
  const [timeElapsed, setTimeElapsed] = useState(0);
  const timerRef = useRef(null);
  const [showSolverBoard, setShowSolverBoard] = useState(false);
  //  Variáveis de tabuleiro
  const [solverBoard, setSolverBoard] = useState(null);
  const [data, setData] = useState(null);


  const fetchSolverBoard = async () => {
    try {
      const data = await apiSolver();
      setSolverBoard(data);
    } catch (error) {
      console.error('falha', error);
    }
  };
  

  const fetchBoard = async () => {
    try {
      const board = await apiBoard();
      setData(board);
    } catch (error) {
      console.error('falha em achar o board', error);
    }
  };



  const updateBoardData = (newData) => {
    setData(newData);
  };
  


  console.log(started);
  

  const initializeGame = async () => {
    try {
      await resetGame();
      // const data = await apiSolver();
      const data = await apiBoard();
      setSolverBoard(data);
      setData(data);
    } catch (error) {
      console.error('Failed to initialize the game', error);
    }
  };

  useEffect(() => {
    initializeGame();
  }, []);


  useEffect(() => {
    if (started) {
      timerRef.current = setInterval(() => {
        setTimeElapsed((prev) => prev + 1);
      }, 1000);
    } else {
      clearInterval(timerRef.current);
      setTimeElapsed(0);
    }

    return () => {
      clearInterval(timerRef.current);
    };
  }, [started]);


  const resetTimer = () => {
    setTimeElapsed(0);
    clearInterval(timerRef.current);
  };


  const onGameOver = (isGameOver) => {
    setGameOver(isGameOver);
  };



  const generateGrid = async () => {
    try {
      const responseBoard = await axios.get('http://localhost:8081/api/board');
      console.log('Tabuleiro gerado', responseBoard.data);
      setGrid(responseBoard.data);
    } catch {
      console.error('erro ao gerar tabuleiro');
    }
  };



  const handleResetGame = async () => {
    try {
      await resetGame();
      setGameOver(false);
      setStarted(false);
      await generateGrid();
      fetchSolverBoard();
      fetchBoard();
      // resetTimer();
    } catch (error) {
      console.error('Failed to reset the game', error);
    }
  };

  useEffect(() => {
    // generateGrid();
  }, [started]);

  return (
    <main className="h-screen w-screen py-20">
      {/* <Navbar></Navbar> */}
      <Buttons
        selected={difficulty}
        setSelected={setDifficulty}
        setStarted={setStarted}
        generateGrid={generateGrid}
        resetTimer={resetTimer}
        // Fetch para buscar buscar tabuleiros quando o jogo for iniciado
        fetchSolverBoard={fetchSolverBoard}
        fetchBoard={fetchBoard}
        setShowSolverBoard={setShowSolverBoard}
      />

      <div className="mx-auto w-fit py-4 flex">
        <div className='board-container' >
          <Board
            className="board"
            boardData={data}
            updateBoardData={updateBoardData}
            setGrid={setGrid}
            onGameOver={onGameOver}
            timeElapsed={timeElapsed}
            setStarted={setStarted}
            started={started}
            resetGame={handleResetGame}
          />
          {showSolverBoard && 
            <SolverBoard 
              className="solver" 
              solverBoard={solverBoard} 
              setShowSolverBoard={setShowSolverBoard}
            />}
        </div>

        {gameOver && (
          <ModalGameOver
            resetGame={handleResetGame}
            setStarted={setStarted}
            setShowSolverBoard={setShowSolverBoard}
            generateGrid={generateGrid}
            fetchSolverBoard={fetchSolverBoard}
          />
        )}
      </div>
      <div>
      </div>
      {/* <div>
        <Teste data={data} />
      </div> */}
    </main>
  );
};

export default App;
