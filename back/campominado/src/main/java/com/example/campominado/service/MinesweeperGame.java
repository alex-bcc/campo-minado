package com.example.campominado.service;

import com.example.campominado.model.Cell;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Getter
@Setter
@Service
public class MinesweeperGame {
    private int rows = 10;
    private int cols = 10;
    private int totalMines;
    private final int minesPorcent = 10;

    private Cell[][] solverBoard;
    private Cell[][] board;

    public MinesweeperGame() {
        resetGame();
    }

    @PostConstruct
    public void init() {
        this.board = new Cell[rows][cols];
        initializeBoard();
        placeMines();
        calculateAdjacentMines();
    }

    public void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public void placeMines() {
        Random random = new Random();
        int rand_val;

        totalMines = 0;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                rand_val = random.nextInt(100);

                if (rand_val < minesPorcent) {
                    board[x][y].isMine = true;
                    totalMines ++;
                }
                else
                    board[x][y].isMine = false;
            }
        }
    }

    public void calculateAdjacentMines() {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},  {0, 0},   {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int[] dir : directions) {
                    int newX = i + dir[0];
                    int newY = j + dir[1];

                    if (isValid(newX, newY) && board[newX][newY].isMine) {
                        board[i][j].adjacentMines++;
                    }
                }
            }
        }
    }

    boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }



    public void revealDepthSearch(int x, int y) {
        if (!isValid(x, y) || board[x][y].isRevealed) {
            return;
        }
        board[x][y].isRevealed = true;
        if (board[x][y].isMine) {
            System.out.println("Game Over! You hit a mine!");
            return;
        }
        if (board[x][y].adjacentMines == 0) {
            int[][] directions = {
                    {-1, -1}, {-1, 0}, {-1, 1},
                    {0, -1},  {0, 0},   {0, 1},
                    {1, -1}, {1, 0}, {1, 1}
            };
            for (int[] dir : directions) {
                int newX = x + dir[0];
                int newY = y + dir[1];
                revealDepthSearch(newX, newY);
            }
        }
    }




    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j].isRevealed) {
                    if (board[i][j].isMine) {
                        System.out.print("X ");
                    } else {
                        System.out.print(board[i][j].adjacentMines + " ");
                    }
                } else if(board[i][j].isFlagged){
                    System.out.print("! ");
                }else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printSolverBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                    if (solverBoard[i][j].isFlagged) {
                        System.out.print("X ");
                    } else {
                        System.out.print(solverBoard[i][j].adjacentMines + " ");
                    }

            }
            System.out.println();
        }
        System.out.println();
    }

    public void revealMines() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                    if (board[i][j].isMine) {
                        System.out.print("X ");
                    } else {
                        System.out.print(board[i][j].adjacentMines + " ");
                    }

            }
            System.out.println();
        }
        System.out.println();
    }

    public void resetGame() {
        this.board = new Cell[rows][cols];

        initializeBoard();
        placeMines();
        calculateAdjacentMines();

        solverBoard = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Certifique-se de criar uma nova instância de 'Cell' para a cópia
                Cell cell = board[i][j];
                solverBoard[i][j] = new Cell(cell.getX(),cell.getY(),cell.isMine(),cell.getAdjacentMines(),cell.isRevealed(),cell.isFlagged());
            }
        }
    }







}
