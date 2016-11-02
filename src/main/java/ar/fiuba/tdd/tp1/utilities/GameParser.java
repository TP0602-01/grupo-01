package ar.fiuba.tdd.tp1.utilities;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.CellViewFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.LinkingSymbolsTable;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteLinker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.set.CellSet;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.CellView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GameParser {
    private Game game;
    private GameBoard gameBoard;
    private BoardView boardView;
    private InputValidator inputValidator;

    private JSONParser parser;
    private JSONObject jsonStructure;
    private JSONObject jsonRules;

    private JSONObject jsonLinkingSymbolsTable;
    private JSONObject jsonLinkingTable;

    private LinkingSymbolsTable linkingSymbolsTable;
    private LinkingTable linkingTable;

    /*  */
    public GameParser(String folderGamePath) {
        gameBoard = null;
        boardView = null;
        linkingSymbolsTable = null;
        String possibleInputFileName = folderGamePath + "/input.txt";
        inputValidator = new InputValidator(possibleInputFileName);
        parser = new JSONParser();

        String structureFileName = folderGamePath + "/structure.json";
        String rulesFileName = folderGamePath + "/rules.json";
        String linkingSymbolsTableFileName = folderGamePath + "/linking_symbols.json";
        String linkingTableFileName = folderGamePath + "/linking_table.json";
        initFiles(structureFileName, rulesFileName, linkingSymbolsTableFileName, linkingTableFileName);
    }


    private JSONObject parseFileToJsonObject(String fileName) throws Exception {
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        return (JSONObject) parser.parse(fileReader);
    }

    /*  */
    private void initFiles(String structureFileName, String rulesFileName,
                           String linkingSymbolsTableFileName, String linkingTableFileName) {
        try {
            jsonStructure = parseFileToJsonObject(structureFileName);
            jsonRules = parseFileToJsonObject(rulesFileName);
            jsonLinkingSymbolsTable = parseFileToJsonObject(linkingSymbolsTableFileName);
            jsonLinkingTable = parseFileToJsonObject(linkingTableFileName);

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
        game = new Game(gameBoard, inputValidator);
    }

    /*  */
    private void parseRuleObject(JSONObject rule) {
        // Create Set and add them to Game
        Collection<Rule> setRules = new ArrayList<>();

        Iterator iterator = getJsonArrayIterator(rule, "rules");
        while (iterator.hasNext()) {
            JSONObject cellObject = (JSONObject) iterator.next();
            Vector<String> ruleValues = getValuesInJsonObjectFromKeys(cellObject, new String[]{"type", "value"});
            Rule ruleObject = RuleFactory.create(ruleValues.elementAt(0), ruleValues.elementAt(1), gameBoard);
            setRules.add(ruleObject);
        }

        // Create the cell Set and include it to Game
        Iterator setsIterator = getJsonArrayIterator(rule, "sets");
        while (setsIterator.hasNext()) {
            JSONObject set = (JSONObject) setsIterator.next();
            JSONArray setArray = (JSONArray) set.get("set");

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

            Integer firstX = Integer.parseInt(initialCell.split(",")[0]) - 1;
            Integer firstY = Integer.parseInt(initialCell.split(",")[1]) - 1;
            Integer endX = Integer.parseInt(finalCell.split(",")[0]) - 1;
            Integer endY = Integer.parseInt(finalCell.split(",")[1]) - 1;

            for (int x = firstX; x <= endX; ++x) {
                for (int y = firstY; y <= endY; ++y) {
                    Cell cell = gameBoard.getCell(x, y);
                    cellGraph.addCell(cell);
                }
            }

            /*for (int x = firstX; x <= endX; ++x) {    TO LINK CELLS OF A SET. DONT REMOVE YET
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
            }*/
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

    /*  */
    //TODO: IMPLEMENTAR. LA IDEA ES QUE SE PUEDA LEER DE UN ARCHIVO TODA LA
    //TODO: INFORMACION DE LINKEO Y QUE ESTA SE GUARDE EN UN CLASE LLAMADA TRADUCTOR
    //TODO: ESTA CLASE TRADUCTOR SERA PASADA A LA CLASE GAME, QUE EN CADA JUGADA SERA
    //TODO: LA ENCARGADA DE GENERAR LOS CONJUNTOS VARIABLES.
    public void parseLinkingInformation() {

        //De aca sale com el linkengSymbolsTable cargado
        linkingSymbolsTable = new LinkingSymbolsTable();
        iterateJsonArray((JSONArray) jsonLinkingSymbolsTable.get("LinkingSymbols"), new ParserFunctorLinkingSymbol());
        //ahora ahi que hacer lo mismo con la linking Table
        linkingTable = new LinkingTable();
        iterateJsonArray((JSONArray) jsonLinkingTable.get("LinkingTable"), new ParserFunctorLinkingTable());

        game.setLinker(new ConcreteLinker(gameBoard, linkingTable, linkingSymbolsTable));

    }

    private void parseLinkingSymbolObject(JSONObject linkingSymbol) {

        Set<String> symbolsLinkingTokens = new HashSet<>();

        String symbol = (String) linkingSymbol.get("symbol");
        JSONArray linkingTokens = (JSONArray) linkingSymbol.get("linkingTokens");
        Iterator tokensIterator = linkingTokens.iterator();
        while (tokensIterator.hasNext()) {
            symbolsLinkingTokens.add((String) tokensIterator.next());
        }

        linkingSymbolsTable.setSymbolsLinkingTokens(symbol, symbolsLinkingTokens);
    }

    private void parseLinkingTableObject(JSONObject linkingTableEntryObject) {

        int rowOffset = ((Long) linkingTableEntryObject.get("rowOffset")).intValue();
        int columnOffset = ((Long) linkingTableEntryObject.get("columnOffset")).intValue();

        Iterator iterator = getJsonArrayIterator(linkingTableEntryObject, "tokensCombinations");
        while (iterator.hasNext()) {
            JSONObject tokensCombination = (JSONObject) iterator.next();
            String originToken = (String) tokensCombination.get("originToken");

            Iterator tokensIterator = getJsonArrayIterator(tokensCombination, "destinationTokens");
            while (tokensIterator.hasNext()) {
                linkingTable.addEntry(rowOffset, columnOffset, originToken, (String) tokensIterator.next());
            }
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

    private class ParserFunctorLinkingSymbol extends ParserFunctor {
        void parse(JSONObject object) {
            parseLinkingSymbolObject(object);
        }
    }

    private class ParserFunctorLinkingTable extends ParserFunctor {
        void parse(JSONObject object) {
            parseLinkingTableObject(object);
        }
    }

    private Iterator getJsonArrayIterator(JSONObject jsonObject, String arrayKey) {
        JSONArray rules = (JSONArray) jsonObject.get(arrayKey);
        return rules.iterator();
    }

    private Vector<String> getValuesInJsonObjectFromKeys(JSONObject object, String[] keys) {
        Vector<String> values = new Vector<>();
        for (int i = 0; i < keys.length; i++) {
            values.add( (String)object.get(keys[i]) );
        }
        return values;
    }
}
