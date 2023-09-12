package com.example.campominado.service;

import com.example.campominado.model.Cell;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class MinesweeperSolver {
    private final MinesweeperGame game;

    private final int[][] directions = {
            {-1, 0}, {0, 0}, {1, 0},
            {-1, -1},  {0, -1},   {1, -1},
            {-1, 1}, {0, 1}, {1, 1}
    };


    public MinesweeperSolver(MinesweeperGame game) {
      this.game = game;
        for (int i = 0; i < game.getRows() ; i++) {
            for (int j = 0; j < game.getCols(); j++) {

                    System.out.print(game.getSolverBoard()[i][j].getAdjacentMines() + " ");

            }
            System.out.println();
        }
        System.out.println();
    }

    boolean isSafe(int x, int y) {
        if (!game.isValid(x, y))
            return false;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (game.isValid(newX, newY)){
                Cell cell = game.getSolverBoard()[newX][newY];
                if(cell.adjacentMines - 1 < 0)
                    return false;
            }
        }
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (game.isValid(newX,  newY)) {
                Cell cell = game.getSolverBoard()[newX][newY];
                cell.adjacentMines--;
            }
        }
        return true;
    }


    boolean findUnvisited(
                                 int[] x, int[] y)
    {
        for (int i = 0; i < game.getRows() ; i++) {
            for (int j = 0; j < game.getCols(); j++)
                if (!game.getSolverBoard()[i][j].isRevealed) {
                    x[0]=i;
                    y[0]=j;
                    return true;
                }

        }
        return false;

    }

     boolean isDone()
    {
        boolean done = true;
        for (int i = 0; i < game.getRows() ; i++) {
            for (int j = 0; j < game.getCols(); j++) {
                int adjacent = game.getSolverBoard()[i][j].adjacentMines;
                done = done && (adjacent == 0)
                        && game.getSolverBoard()[i][j].isRevealed();
            }
        }

        return done;
    }

    public boolean SolveMinesweeper() {
        boolean concluido = isDone();
        if (concluido)
            return true;
        int[] x = { 0 };
        int[] y = { 0 };
        if (!findUnvisited(x, y))
            return false;
        game.getSolverBoard()[x[0]][y[0]].isRevealed = true;
        if (isSafe(x[0], y[0])) {
            game.getSolverBoard()[x[0]][y[0]].isFlagged = true;
            if (SolveMinesweeper())
                return true;
            game.getSolverBoard()[x[0]][y[0]].isFlagged = false;
            for (int[] dir : directions) {
                int newX = x[0] + dir[0];
                int newY = y[0] + dir[1];
                if (game.isValid(newX, newY))
                    game.getSolverBoard()[newX][newY].adjacentMines++;
            }
        }
        if (SolveMinesweeper())
            return true;
        game.getSolverBoard()[x[0]][y[0]].isRevealed = false;
        return false;
    }


    public void minesweeperOperations() {
        // Se a solução para a matriz de Campo Minado de entrada existe
        if (SolveMinesweeper()) {
            // Chamada de função para imprimir o tabuleiro[][]
            game.printSolverBoard();
        } else {
            // Nenhuma solução existe
            System.out.println("Nenhuma solução existe");
        }
    }






}
