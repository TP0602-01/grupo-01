package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameLoop {

    GameBoard gameBoard;
    BufferedReader reader;

    public GameLoop(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException{
        //while (!gameBoard.isFull()) {

        for(int i = 0; i  < 4 ; i++){

            InputCellData data = new InputCellData(reader.readLine());
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

        public int getIndexI() {
            return indexI;
        }

        public int getIndexJ() {
            return indexJ;
        }

        public Integer getInputData() {
            return data;
        }
    }

}
