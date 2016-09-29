package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.nio.charset.Charset;

public class GameLoop {

    GameBoard gameBoard;
    Scanner scanner;

    public GameLoop(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        System.setProperty("file.encoding","UTF-8");
        Field charset = null;
        try {
            charset =Charset.class.getDeclaredField("defaultCharset");

            charset.setAccessible(true);
            charset.set(null,null);
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        while (!gameBoard.isFull()) {
            InputCellData data = new InputCellData(readLine());
            gameBoard.setCellValue(data.getIndexI(), data.getIndexJ(), data.getInputData());
        }
    }

    private String readLine() throws IOException {
        return scanner.nextLine();
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
