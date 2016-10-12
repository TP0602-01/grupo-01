package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.game.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* */
public class GameLoop implements GameBoardController {

    private Game game;

    public GameLoop(Game game) {
        this.game = game;
    }

    /* Start the game.
     * If user win, inform that with a message and finish the Game
     * If user make an invalid play, inform that with a message and continue the game */
    public void start() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        boolean gameState = game.checkRules();
        while ( !gameState ) {
            InputCellData data = new InputCellData(console.readLine());
            game.addPlay(data.getIndexI(), data.getIndexJ(), data.getInputData());  //TODO: CHEQUEAR SI LA JUGADA FUE CORRECTA
            gameState = game.checkRules();
            System.out.println("Estado del tablero: " + gameState );
        }
    }

    /* User Play */
    private static class InputCellData {
        private int indexI;
        private int indexJ;
        private String data;

        InputCellData(String line) {
            String[] splited = line.split(" ");
            indexI = Integer.parseInt(splited[0]);
            indexJ = Integer.parseInt(splited[1]);
            data = splited[2];
        }

        int getIndexI() {
            return indexI - 1;
        }

        int getIndexJ() {
            return indexJ - 1;
        }

        String getInputData() {
            return data;
        }
    }

}
