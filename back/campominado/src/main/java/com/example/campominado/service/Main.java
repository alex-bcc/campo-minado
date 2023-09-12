package com.example.campominado.service;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {



        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Campo Minado!");


        MinesweeperGame game = new MinesweeperGame();
        game.printBoard();
        System.out.println(game.getTotalMines());
        game.revealMines();

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Escolha uma ação:");
            System.out.println("1. Revelar célula");
            System.out.println("2. Sair do jogo");
            System.out.println("3. Resolver com Busca profunda");
            System.out.println("4. Resetar");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Informe a linha: ");
                    int x = scanner.nextInt();
                    System.out.print("Informe a coluna: ");
                    int y = scanner.nextInt();
                    game.revealDepthSearch(x, y);
                    game.printBoard();
                    break;
                case 2:
                    System.out.println("Jogo encerrado. Obrigado por jogar!");
                    gameOver = true;
                    break;
                case 3:
                    game.revealMines();
                    MinesweeperSolver minesweeperSolver = new MinesweeperSolver(game);
                    minesweeperSolver.minesweeperOperations();
                    break;
                case 4:
                    game.resetGame();
                    game.printBoard();
                    game.revealMines();
                    game.printSolverBoard();
                    break;

                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        }

        scanner.close();
    }
}
