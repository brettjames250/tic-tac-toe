package com.projects;

public class Main {

    public static void main(String[] args) {
          Game newGame = new Game();
        newGame.printGame();

        while (newGame.gameState == GameState.NOT_FINISHED) {
            newGame.playGame();
        }
    }
}
