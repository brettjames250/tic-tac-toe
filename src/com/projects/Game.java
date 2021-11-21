package com.projects;

import java.util.Scanner;

public class Game {
    boolean gameOngoing;
    int numX;
    int numO;
    char[][] gameGrid;
    Player player;
    GameState gameState;


    public Game() {
        this.gameOngoing = true;
        this.numX = 0;
        this.numO = 0;
        this.gameGrid = new char[][]{{'_', '_', '_'}, {'_', '_', '_'}, {'_', '_', '_'}};
        this.player = Player.X;
        this.gameState = GameState.NOT_FINISHED;
    }

    void playGame() {
        switch (gameState) {
            case NOT_FINISHED:
                getUserInput();
                break;
            case X_WINS:
                System.out.println("X wins");
            case O_WINS:
                System.out.println("O wins");
            case DRAW:
            case IMPOSSIBLE:
        }
    }

    void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean badInput = true;
        while (gameState == GameState.NOT_FINISHED) {
            System.out.print("Enter the coordinates: ");
            String input = scanner.nextLine();
            String[] arr = input.split(" ");
            int[] coordinates = new int[2];

            try {
                int row = Integer.parseInt(arr[0]);
                int col = Integer.parseInt(arr[1]);
                coordinates[0] = row;
                coordinates[1] = col;
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if ((coordinates[0] < 1 || coordinates[0] > 3) || (coordinates[1] < 1 || coordinates[1] > 3)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (gameGrid[coordinates[0] - 1][coordinates[1] - 1] != '_') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            if (player == Player.X) {
                gameGrid[coordinates[0] - 1][coordinates[1] - 1] = 'X';
                numX++;
                player = Player.O;
            } else if (player == Player.O) {
                gameGrid[coordinates[0] - 1][coordinates[1] - 1] = 'O';
                player = Player.X;
                numO++;
            }
            printGame();
            checkGame();
        }
    }

    void checkGame() {
        // check rows
        for (int i = 0; i < gameGrid.length; i++) {
            if ((gameGrid[i][0] == gameGrid[i][1]) && (gameGrid[i][1] == gameGrid[i][2])) {
                if (gameGrid[i][0] == 'X') {
                    gameState = GameState.X_WINS;
                    System.out.println("X wins");
                } else if (gameGrid[i][0] == 'O') {
                    gameState = GameState.O_WINS;
                    System.out.println("O wins");
                }
            }
        }

        // check columns
        for (int j = 0; j < gameGrid[0].length; j++) {
            if ((gameGrid[0][j] == gameGrid[1][j]) && (gameGrid[1][j] == gameGrid[2][j])) {
                if (gameGrid[0][j] == 'X') {
                    gameState = GameState.X_WINS;
                    System.out.println("X wins");
                } else if (gameGrid[0][j] == 'O') {
                    gameState = GameState.O_WINS;
                    System.out.println("O wins");
                }
            }
        }

        // check diagonals
        if ((gameGrid[0][0] == gameGrid[1][1]) && (gameGrid[1][1] == gameGrid[2][2])) {
            if (gameGrid[0][0] == 'X') {
                gameState = GameState.X_WINS;
                System.out.println("X wins");
            } else if (gameGrid[0][0] == 'O') {
                gameState = GameState.O_WINS;
                System.out.println("O wins");
            }
        } else if ((gameGrid[2][0] == gameGrid[1][1]) && (gameGrid[1][1] == gameGrid[0][2])) {
            if (gameGrid[1][1] == 'X') {
                gameState = GameState.X_WINS;
                System.out.println("X wins");
            } else if (gameGrid[1][1] == 'O') {
                gameState = GameState.O_WINS;
                System.out.println("O wins");
            }
        }
    }

    void printGame() {
        System.out.println("---------");
        for (int i = 0; i < gameGrid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < gameGrid[i].length; j++) {
                System.out.print(gameGrid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}
