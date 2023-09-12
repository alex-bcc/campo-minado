package com.example.campominado.service;
import java.util.*;

public class Teste {

        static int N, M;
        static final int MAXM = 100;
        static final int MAXN = 100;
    // Stores the final generated input
    static int arr[][] = new int[100][100];

    // Direction arrays
    static int dx[] = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static int dy[] = { 0, 0, 0, -1, -1, -1, 1, 1, 1 };


    // Function to generate a valid minesweeper
    // matrix of size ROW * COL with P being
    // the probability of a cell being a mine
    static void generateMineField(int ROW, int COL, int P)
    {
        // Generates the random
        // number every time
        Random random = new Random();

        int rand_val;

        // Stores whether a cell
        // contains a mine or not
        boolean mines[][] = new boolean[ROW][COL];

        // Iterate through each cell
        // of the matrix mine
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                // Generate a random value
                // from the range [0, 100]
                rand_val = random.nextInt(100);

                // If rand_val is less than P
                if (rand_val < P)

                    // MArk mines[x][y] as True
                    mines[x][y] = true;

                    // Otherwise, mark
                    // mines[x][y] as False
                else
                    mines[x][y] = false;
            }
        }

        System.out.println("Generated Input:");

        // Iterate through each cell (x, y)
        for (int x = 0; x < ROW; x++) {
            for (int y = 0; y < COL; y++) {
                arr[x][y] = 0;

                // Count the number of mines
                // around the cell (x, y)
                // and store in arr[x][y]
                for (int k = 0; k < 9; k++) {
                    // If current adjacent cell is valid
                    if (isValid(x + dx[k], y + dy[k])
                            && (mines[x + dx[k]][y + dy[k]]))
                        arr[x][y]++;
                }

                // Print the value at
                // the current cell
                System.out.print(arr[x][y] + " ");
            }
            System.out.println();
        }
    }

        // Function to check if the
        // cell (x, y) is valid or not
        static boolean isValid(int x, int y)
        {
            return (x >= 0 && y >= 0 && x < N && y < M);
        }

        // Function to print the matrix grid[][]
        static void printGrid(boolean[][] grid)
        {
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < M; col++) {
                    if (grid[row][col])
                        System.out.print("x ");
                    else
                        System.out.print("_ ");
                }
                System.out.println();
            }
        }

        // Function to check if the cell (x, y)
        // is valid to have a mine or not
        static boolean isSafe(int[][] arr, int x, int y)
        {

            // Check if the cell (x, y) is a
            // valid cell or not
            if (!isValid(x, y))
                return false;

            // Check if any of the neighbouring cell
            // of (x, y) supports (x, y) to have a mine
            for (int i = 0; i < 9; i++) {
                if (isValid(x + dx[i], y + dy[i])
                        && (arr[x + dx[i]][y + dy[i]] - 1 < 0))
                    return false;
            }

            // If (x, y) is valid to have a mine
            for (int i = 0; i < 9; i++) {
                if (isValid(x + dx[i], y + dy[i]))

                    // Reduce count of mines in
                    // the neighboring cells
                    arr[x + dx[i]][y + dy[i]]--;
            }

            return true;
        }

        // Function to check if there
        // exists any unvisited cell or not
        static boolean findUnvisited(boolean[][] visited,
                                     int[] x, int[] y)
        {
            for (x[0] = 0; x[0] < N; x[0]++)
                for (y[0] = 0; y[0] < M; y[0]++)
                    if (!visited[x[0]][y[0]])
                        return true;
            return false;
        }

        // Function to check if all the cells
        // are visited or not and the input array
        // is satisfied with the mine assignments
        static boolean isDone(int[][] arr, boolean[][] visited)
        {
            boolean done = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    done = done && (arr[i][j] == 0)
                            && visited[i][j];
                }
            }

            return done;
        }

        // Function to solve the minesweeper matrix
        static boolean SolveMinesweeper(boolean[][] grid,
                                        int[][] arr,
                                        boolean[][] visited)
        {

            // Function call to check if each cell
            // is visited and the solved grid is
            // satisfying the given input matrix
            boolean done = isDone(arr, visited);

            // If the solution exists and
            // and all cells are visited
            if (done)
                return true;

            int[] x = { 0 };
            int[] y = { 0 };

            // Function call to check if all
            // the cells are visited or not
            if (!findUnvisited(visited, x, y))
                return false;

            // Mark cell (x, y) as visited
            visited[x[0]][y[0]] = true;

            // Function call to check if it is
            // safe to assign a mine at (x, y)
            if (isSafe(arr, x[0], y[0])) {

                // Mark the position with a mine
                grid[x[0]][y[0]] = true;

                // Recursive call with (x, y) having a mine
                if (SolveMinesweeper(grid, arr, visited))
                    return true;

                // Reset the position x, y
                grid[x[0]][y[0]] = false;
                for (int i = 0; i < 9; i++) {
                    if (isValid(x[0] + dx[i], y[0] + dy[i]))
                        arr[x[0] + dx[i]][y[0] + dy[i]]++;
                }
            }

            // Recursive call without (x, y) having a mine
            if (SolveMinesweeper(grid, arr, visited))
                return true; // If solution exists then return
            // true

            // Mark the position as unvisited again
            visited[x[0]][y[0]] = false;

            // If no solution exists
            return false;
        }

        public static void minesweeperOperations(int[][] arr,
                                                 int N, int M)
        {

            // Stores the final result
            boolean[][] visited = new boolean[N][M];

            // Stores whether the position
            // (x, y) is visited or not
            boolean[][] grid = new boolean[N][M];
            // If the solution to the input
            // minesweeper matrix exists
            if (SolveMinesweeper(grid, arr, visited))
                // Function call to print the grid[][]
                printGrid(grid);
                // No solution exists
            else
                System.out.println("No solution exists");
        }

        public static void main(String[] args)
        {
                // Given input
            N = 7;
            M = 7;
            int P = 30;
            generateMineField(N,M,P);
            int[][] arr = { { 1, 1, 0, 0, 1, 1, 1 },
                            { 2, 3, 2, 1, 1, 2, 2 },
                            { 3, 5, 3, 2, 1, 2, 2 },
                            { 3, 6, 5, 3, 0, 2, 2 },
                            { 2, 4, 3, 2, 0, 1, 1 },
                            { 2, 3, 3, 2, 1, 2, 1 },
                            { 1, 1, 1, 1, 1, 1, 0 } };

            minesweeperOperations(arr, N, M);
        }


}
