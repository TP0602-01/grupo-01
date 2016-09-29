package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.CellViewFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.factory.WalkFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.CellView;
import ar.fiuba.tdd.tp1.walk.Walk;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        // PARAMETROS QUE SE TOMAN DE ARCHIVO
        int boardWidth = 9;
        int boardHeight = 9;

        // Initialize Structure Game

        // Board
        GameBoard gameBoard = new GameBoard(boardWidth, boardHeight);
        BoardView boardView = new BoardView(gameBoard);

        // Rules and Walks
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("/home/juanma/Tecnicas de Diseño/grupo-01/src/main/java/ar/fiuba/tdd/tp1/rules.json");
            JSONObject json = (JSONObject) parser.parse(fileReader);
            JSONArray rules = (JSONArray) json.get("rules");

            Iterator it = rules.iterator();
            while (it.hasNext()) {
                JSONObject rule = (JSONObject) it.next();
                String type = (String) rule.get("type");
                String walk = (String) rule.get("walk");
                JSONArray cells = (JSONArray) rule.get("init_cells");

                // Todas las celdas ponele el sudoku regla que sea no rep para abajo
                Collection<String> cellPositions = new ArrayList<String>();
                Iterator jt = cells.iterator();
                while (jt.hasNext()) {
                    JSONObject cellObject = (JSONObject) jt.next();
                    String cellPosition = (String) cellObject.get("cell");
                    cellPositions.add(cellPosition);

                    System.out.print(cellPosition);
                }

                Walk walkObject = WalkFactory.create(gameBoard, walk);
                BaseRule ruleObject = RuleFactory.create(type, walkObject, cellPositions);
                gameBoard.addRule(ruleObject);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Celdas
        try {
            FileReader fileReader = new FileReader("/home/juanma/Tecnicas de Diseño/grupo-01/src/main/java/ar/fiuba/tdd/tp1/structure.json");
            JSONObject json = (JSONObject) parser.parse(fileReader);
            JSONObject structure = (JSONObject) json.get("structure");
            JSONArray cell = (JSONArray) structure.get("cells");

            Iterator it = cell.iterator();
            while (it.hasNext()) {
                JSONObject rule = (JSONObject) it.next();
                String type = (String) rule.get("type");
                String range = (String) rule.get("range");

                System.out.print("\ntipo: " + type + "\nrange: " + range +"\n");

                Integer firxtX = Integer.parseInt(range.split("-")[0].split(",")[0]);
                Integer firxtY = Integer.parseInt(range.split("-")[0].split(",")[1]);
                Integer endX = Integer.parseInt(range.split("-")[1].split(",")[0]);
                Integer endY = Integer.parseInt(range.split("-")[1].split(",")[1]);

                System.out.println(firxtX + "  " + firxtY + "   "+ endX + "   "+ endY);

                for (int x = firxtX; x <= endX; ++x) {
                    for (int y = firxtY; y <= endY; ++y) {
                        Cell cellObject = CellFactory.create(type);
                        CellView cellView = CellViewFactory.create(cellObject, type);
                        gameBoard.addCell(x, y, cellObject);
                        boardView.addCellViewIn(cellView, x, y);
                    }
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        boardView.update();

        // Enter View
        gameBoard.registerObserver(boardView);
        boolean gameFinished = false;

        // Start Game
        while (!gameFinished) {
            if (gameBoard.checkRules()) {
                gameFinished = true;
                continue;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a movement: ");
            try {
                String input = bufferedReader.readLine();
                // CHECKEAR EL INPUT!!!!
                String value = input.split(",")[0];
                Integer posX = Integer.parseInt(input.split(",")[1]);
                Integer posY = Integer.parseInt(input.split(",")[2]);

                /*
                Cell newCell = new InputCell(value);
                gameBoard.addCell(posX, posY, newCell);
                */

                //((InputCell)(gameBoard.getCell(posX, posY))).setData(value);
                int cont = 1;
                for (int i=0; i <= 8; i++){
                    for (int j=0; j <= 8; j++){
/*
                        for (int k=1; k <= 9; k++) {
                            value = String.valueOf((k*cont) % (9) + 1);
                            ((InputCell) (gameBoard.getCell(i, j))).setData(value);
                        }
*/                      ((InputCell) (gameBoard.getCell(i, j))).setData(String.valueOf((i + j) % 9 + 1));

                        cont++;
                    }
                }


                boardView.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("The Game have finished, you Wiiin!!!");
    }
}





