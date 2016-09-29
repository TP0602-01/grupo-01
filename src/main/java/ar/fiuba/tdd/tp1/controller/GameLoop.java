package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameLoop {

    GameBoard gameBoard;

    public GameLoop(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void start() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        while (!gameBoard.isFull()) {
            InputCellData data = new InputCellData(console.readLine());
            gameBoard.setCellValue(data.getIndexI(), data.getIndexJ(), data.getInputData());
        }
    }

    private class InputCellData {
        private int indexI;
        private int indexJ;
        private Integer data;

        InputCellData(String line) {
            String[] splited = line.split(" ");
            indexI = Integer.parseInt(splited[0]);
            indexJ = Integer.parseInt(splited[1]);
            data = Integer.parseInt(splited[2]);
        }

        int getIndexI() {
            return indexI;
        }

        int getIndexJ() {
            return indexJ;
        }

        Integer getInputData() {
            return data;
        }
    }

}
