package ar.fiuba.tdd.tp1;

/*import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.factory.WalkFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.view.BoardView;
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
import java.util.Iterator;*/


import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // PARAMETROS QUE SE TOMAN DE ARCHIVO
       /* int boardWidth = 9;
        int boardHeight = 9;

        // Initialize Structure Game

        // Board
        GameBoard gameBoard = new GameBoard(boardWidth, boardHeight);
        BoardView boardView = new BoardView(gameBoard);

        // Rules and Walks
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("rules.json");
            JSONObject json = (JSONObject) parser.parse(fileReader);
            JSONArray rules = (JSONArray) json.get("rules");

            Iterator it = rules.iterator();
            while (it.hasNext()) {
                JSONObject rule = (JSONObject) it.next();
                String type = (String) rule.get("type");
                String walk = (String) rule.get("walk");
                JSONArray cells = (JSONArray) rule.get("init_cells");

                Collection<String> cellPositions = new ArrayList<String>();
                Iterator jt = cells.iterator();
                while (jt.hasNext()) {
                    JSONObject cellObject = (JSONObject) jt.next();
                    String cellPosition = (String) cellObject.get("cell");
                    cellPositions.add(cellPosition);
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
            FileReader fileReader = new FileReader("structure.json");
            JSONObject json = (JSONObject) parser.parse(fileReader);
            JSONObject structure = (JSONObject) json.get("structure");
            JSONArray cell = (JSONArray) structure.get("cells");

            Iterator it = cell.iterator();
            while (it.hasNext()) {
                JSONObject rule = (JSONObject) it.next();
                String type = (String) rule.get("type");
                String range = (String) rule.get("range");

                Integer firxtX = Integer.parseInt(range.split("-")[0].split(",")[0]);
                Integer firxtY = Integer.parseInt(range.split("-")[0].split(",")[1]);
                Integer endX = Integer.parseInt(range.split("-")[1].split(",")[0]);
                Integer endY = Integer.parseInt(range.split("-")[1].split(",")[1]);

                for (int x = firxtX; x < endX; ++x) {
                    for (int y = firxtY; y < endY; ++y) {
                        Cell cellObject = CellFactory.create(type);
                        gameBoard.addCell(x, y, cellObject);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
                Integer value = Integer.parseInt(input.split(",")[0]);
                Integer posX = Integer.parseInt(input.split(",")[1]);
                Integer posY = Integer.parseInt(input.split(",")[2]);

                Cell newCell = new InputCell(value);
                gameBoard.addCell(posX, posY, newCell);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print("The Game have finished, you Wiiin!!!");*/
        GameBoard gameBoard = new GameBoard(2,2);
        gameBoard.addCell(0,0,new InputCell(0));
        gameBoard.addCell(0,1,new InputCell(0));
        gameBoard.addCell(1,0,new FixedCell(0));
        gameBoard.addCell(1,1,new InputCell(0));
        GameLoop gameLoop = new GameLoop(gameBoard);

        try {
            gameLoop.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}





