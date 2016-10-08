package ar.fiuba.tdd.tp1.utilities;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.CellViewFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.factory.WalkFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.CellView;
import ar.fiuba.tdd.tp1.walk.Walk;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameParser {
    private GameBoard gameBoard;
    private BoardView boardView;

    private JSONParser parser;

    private JSONObject jsonStructure;
    private JSONObject jsonRules;

    public GameParser(String structureFileName, String rulesFileName) {
        gameBoard = null;
        boardView = null;
        parser = new JSONParser();
        initFiles(structureFileName, rulesFileName);
    }

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

    private void parseCellObject(JSONObject cell) {
        String type = (String) cell.get("type");
        String range = (String) cell.get("range");

        Integer firstX = Integer.parseInt(range.split("-")[0].split(",")[0]);
        Integer firstY = Integer.parseInt(range.split("-")[0].split(",")[1]);
        Integer endX = Integer.parseInt(range.split("-")[1].split(",")[0]);
        Integer endY = Integer.parseInt(range.split("-")[1].split(",")[1]);

        String content = (String) cell.get("content");



        for (int x = firstX; x <= endX; ++x) {
            for (int y = firstY; y <= endY; ++y) {
                Cell cellObject = CellFactory.create(type, content);
                CellView cellView = CellViewFactory.create(cellObject, type);
                gameBoard.addCell(x, y, cellObject);
                boardView.addCellViewIn(cellView, x, y);
            }
        }
    }

    private void parseRuleObject(JSONObject rule) {
        String type = (String) rule.get("type");
        String walk = (String) rule.get("walk");
        JSONArray cells = (JSONArray) rule.get("init_cells");

        Collection<String> cellPositions = new ArrayList<>();
        Iterator iterator = cells.iterator();
        while (iterator.hasNext()) {
            JSONObject cellObject = (JSONObject) iterator.next();
            String cellPosition = (String) cellObject.get("cell");
            cellPositions.add(cellPosition);
        }

        Walk walkObject = WalkFactory.create(gameBoard, walk);

        BaseRule ruleObject = RuleFactory.create(type, walkObject, cellPositions);
        gameBoard.addRule(ruleObject);
    }

    private void iterateJsonArray(JSONArray array, ParserFunctor functor) {
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            functor.parse(object);
        }
    }

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

    public void parseGameRules() {
        try {
            iterateJsonArray((JSONArray) jsonRules.get("rules"), new ParserFunctorRule());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public GameBoard getBoard() {
        return gameBoard;
    }

    public BoardView getView() {
        return boardView;
    }

    protected abstract class ParserFunctor {
        abstract void parse(JSONObject object);
    }

    protected class ParserFunctorCell extends ParserFunctor {
        void parse(JSONObject object) {
            parseCellObject(object);
        }
    }

    protected class ParserFunctorRule extends ParserFunctor {
        void parse(JSONObject object) {
            parseRuleObject(object);
        }
    }
}
