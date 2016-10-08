package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* */
public class GameLoop implements GameBoardController {

    GameBoard gameBoard;

    public GameLoop(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void start() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while (!gameBoard.isFull() || !gameBoard.checkRules()) {
            InputCellData data = new InputCellData(console.readLine());
            gameBoard.setCellValue(data.getIndexI(), data.getIndexJ(), data.getInputData());
            System.out.println("Estado del tablero: " + gameBoard.checkRules());
        }
    }

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
