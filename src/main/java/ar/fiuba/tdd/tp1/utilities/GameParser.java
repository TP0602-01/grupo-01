package ar.fiuba.tdd.tp1.utilities;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.CellViewFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.set.CellSet;
import ar.fiuba.tdd.tp1.set.Graph;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.CellView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameParser {
    private Game game;
    private GameBoard gameBoard;
    private BoardView boardView;

    private JSONParser parser;
    private JSONObject jsonStructure;
    private JSONObject jsonRules;

    /*  */
    public GameParser(String structureFileName, String rulesFileName) {
        gameBoard = null;
        boardView = null;
        parser = new JSONParser();
        initFiles(structureFileName, rulesFileName);
    }

    /*  */
    private void initFiles(String structureFileName, String rulesFileName) {
        try {
            InputStreamReader fileReaderStructure = new InputStreamReader(new FileInputStream(structureFileName), "UTF-8");
            jsonStructure = (JSONObject) parser.parse(fileReaderStructure);
            InputStreamReader fileReaderRules = new InputStreamReader(new FileInputStream(rulesFileName), "UTF-8");
            jsonRules = (JSONObject) parser.parse(fileReaderRules);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*  */
    private void parseCellObject(JSONObject cell) {
        String type = (String) cell.get("type");
        JSONObject range = (JSONObject) cell.get("range");

        String initialCell = (String) range.get("initial_cell");
        String finalCell = (String) range.get("final_cell");

        Integer firstX = Integer.parseInt(initialCell.split(",")[0]);
        Integer firstY = Integer.parseInt(initialCell.split(",")[1]);
        Integer endX = Integer.parseInt(finalCell.split(",")[0]);
        Integer endY = Integer.parseInt(finalCell.split(",")[1]);

        String content = (String) cell.get("content");

        for (int x = firstX; x <= endX; ++x) {
            for (int y = firstY; y <= endY; ++y) {
                Cell cellObject = CellFactory.create(type, content);
                CellView cellView = CellViewFactory.create(cellObject, type);
                gameBoard.addCell(x, y, cellObject);
                boardView.addCellViewIn(cellView, x, y);
            }
        }
        game = new Game(gameBoard);
    }

    /*  */
    private void parseRuleObject(JSONObject rule) {
        // Create Set and add them to Game
        Collection<Rule> setRules = new ArrayList<>();

        JSONArray rules = (JSONArray) rule.get("rules");
        Iterator iterator = rules.iterator();
        while (iterator.hasNext()) {
            JSONObject cellObject = (JSONObject) iterator.next();
            String ruleType = (String) cellObject.get("type");
            String ruleValue = (String) cellObject.get("value");

            Rule ruleObject = RuleFactory.create(ruleType, ruleValue);
            setRules.add(ruleObject);
        }

        // Create the cell Set and include it to Game
        JSONArray sets = (JSONArray) rule.get("sets");
        Iterator setsIterator = sets.iterator();
        while (setsIterator.hasNext()) {    //TODO: CAMBIAR ESTA CONCATENACION DE FOR - WHILE - IF QUE DA ASCO
            JSONObject set = (JSONObject) setsIterator.next();
            JSONArray setArray = (JSONArray)  set.get("set");

            // Create a Graph of Cells
            Graph cellGraph = createGraph(setArray);

            // Create a Cell Set
            CellSet cellSet = new CellSet(cellGraph, setRules);
            game.addSet(cellSet);
        }
    }

    /* Giving a Json Set Array create a Graph of cells */
    private Graph createGraph(JSONArray setArray) {
        Graph cellGraph = new Graph();
        // Iterate Set
        Iterator singleSetIterator = setArray.iterator();
        while (singleSetIterator.hasNext()) {
            JSONObject singleSet = (JSONObject) singleSetIterator.next();
            // Cell
            String initialCell = (String) singleSet.get("initial_cell");
            String finalCell = (String) singleSet.get("final_cell");

            Integer firstX = Integer.parseInt(initialCell.split(",")[0]) - 1;//TODO: CAMBIAR EN EL ARCH DE SET!
            Integer firstY = Integer.parseInt(initialCell.split(",")[1]) - 1;//TODO: NO DEBERIA TENER -1 ACA
            Integer endX = Integer.parseInt(finalCell.split(",")[0]) - 1;
            Integer endY = Integer.parseInt(finalCell.split(",")[1]) - 1;

            for (int x = firstX; x <= endX; ++x) {
                for (int y = firstY; y <= endY; ++y) {
                    Cell firstCell = gameBoard.getCell(x, y);

                    // Add right next Cell
                    if (x + 1 <= endX) {
                        Cell rightSecondCell = gameBoard.getCell(x + 1, y);
                        cellGraph.addNotDirectedLinkBetween(firstCell, rightSecondCell);
                    }
                    // Add down next Cell
                    if (y + 1 <= endY) {
                        Cell downSecondCell = gameBoard.getCell(x, y + 1);
                        cellGraph.addNotDirectedLinkBetween(firstCell, downSecondCell);
                    }
                    if (firstX.equals(endX) && firstY.equals(endY)) {
                        cellGraph.addNotDirectedLinkBetween(firstCell, firstCell);
                    }
                }
            }
        }
        return cellGraph;
    }

    /*  */
    private void iterateJsonArray(JSONArray array, ParserFunctor functor) {
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            functor.parse(object);
        }
    }

    /*  */
    public void parseGameStructure() {
        try {
            gameBoard = new GameBoard(
                    Integer.parseInt((String) jsonStructure.get("width")),
                    Integer.parseInt((String) jsonStructure.get("height")));
            boardView = new BoardView(gameBoard);

            JSONObject structure = (JSONObject) jsonStructure.get("structure");
            iterateJsonArray((JSONArray) structure.get("cells"), new ParserFunctorCell());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*  */
    public void parseGameRules() {
        try {
            iterateJsonArray((JSONArray) jsonRules.get("all_sets"), new ParserFunctorRule());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Game getGame() {
        return game;
    }

    public BoardView getView() {
        return boardView;
    }

    abstract class ParserFunctor {
        abstract void parse(JSONObject object);
    }

    private class ParserFunctorCell extends ParserFunctor {
        void parse(JSONObject object) {
            parseCellObject(object);
        }
    }

    private class ParserFunctorRule extends ParserFunctor {
        void parse(JSONObject object) {
            parseRuleObject(object);
        }
    }
}
