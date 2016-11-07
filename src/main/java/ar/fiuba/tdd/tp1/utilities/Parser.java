package ar.fiuba.tdd.tp1.utilities;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Parser {

    GameBoard gameBoard;

    ArrayList<Cell> getSetCells(JSONArray setArray) {
        ArrayList<Cell> result = new ArrayList<>();
        Iterator singleSetIterator = setArray.iterator();
        while (singleSetIterator.hasNext()) {
            JSONObject singleSet = (JSONObject) singleSetIterator.next();
            // Cell
            String initialCell = (String) singleSet.get("initial_cell");
            String finalCell = (String) singleSet.get("final_cell");

            Integer firstX = Integer.parseInt(initialCell.split(",")[0]) - 1;
            Integer firstY = Integer.parseInt(initialCell.split(",")[1]) - 1;
            Integer endX = Integer.parseInt(finalCell.split(",")[0]) - 1;
            Integer endY = Integer.parseInt(finalCell.split(",")[1]) - 1;

            for (int x = firstX; x <= endX; ++x) {
                for (int y = firstY; y <= endY; ++y) {
                    Cell cell = gameBoard.getCell(x, y);
                    result.add(cell);
                }
            }
        }
        return result;
    }

}
