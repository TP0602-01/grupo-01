package ar.fiuba.tdd.tp1.utilities;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.graph.LinkingSymbolsTable;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteLinker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.set.CellSet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/*
 * Game Parser class is used to parse all information giving
 * the structure, rules, linking files and generate the Game
 * to play.
 *
 */
public class GameParser extends Parser {
    private Game game;
    private InputValidator inputValidator;

    private JSONParser parser;
    private JSONObject jsonStructure;
    private JSONObject jsonRules;

    private JSONObject jsonLinkingSymbolsTable;
    private JSONObject jsonLinkingTable;

    private LinkingSymbolsTable linkingSymbolsTable;
    private LinkingTable linkingTable;


    /*
     * Set Linker to Game.
     *
     */
    public GameParser(String folderGamePath) {
        gameBoard = null;
        linkingSymbolsTable = null;
        String possibleInputFileName = folderGamePath + "/input.txt";
        inputValidator = new InputValidator(possibleInputFileName);
        parser = new JSONParser();

        String structureFileName = folderGamePath + "/structure.json";
        String rulesFileName = folderGamePath + "/rules.json";
        String linkingSymbolsTableFileName = folderGamePath + "/linking_symbols.json";
        String linkingTableFileName = folderGamePath + "/linking_table.json";
        initFiles(structureFileName, rulesFileName, linkingSymbolsTableFileName, linkingTableFileName);

        this.parseContent();
    }


    private JSONObject parseFileToJsonObject(String fileName) throws Exception {
        InputStreamReader fileReader = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        return (JSONObject) parser.parse(fileReader);
    }

    /*
     * Init files
     *
     */
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

    /*
     * Parse Cell Object
     *
     */
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
                gameBoard.addCell(x, y, cellObject);

            }
        }
        game = new Game(gameBoard, inputValidator);
    }

    /*
     * Parse Rule Object
     *
     */
    private void parseRuleObject(JSONObject rule) {
        // Create Set and add them to Game
        Collection<Rule> setRules = new ArrayList<>();

        Iterator iterator = getJsonArrayIterator(rule, "rules");
        while (iterator.hasNext()) {
            JSONObject cellObject = (JSONObject) iterator.next();
            Vector<String> ruleValues = getValuesInJsonObjectFromKeys(cellObject, new String[] {"type", "value"});
            Rule ruleObject = RuleFactory.create(ruleValues.elementAt(0), ruleValues.elementAt(1));
            setRules.add(ruleObject);
        }

        // Create the cell Set and include it to Game
        Iterator setsIterator = getJsonArrayIterator(rule, "sets");
        while (setsIterator.hasNext()) {
            JSONObject set = (JSONObject) setsIterator.next();
            JSONArray setArray = (JSONArray) set.get("set");

            // Create a Graph of Cells
            Queue<IndexedGraph> subgraphs = createSubgraps(setArray);

            // Create a Cell Set
            CellSet cellSet = new CellSet(subgraphs, setRules);
            game.addSet(cellSet);
        }


        this.parseMergedSets(rule, setRules);

    }

    public void parseMergedSets(JSONObject rule, Collection<Rule> setRules) {
        // Create the cell Set and include it to Game
        if (rule.containsKey("mergedSets")) {
            Iterator mergedSetsIterator = getJsonArrayIterator(rule, "mergedSets");
            Queue<IndexedGraph> subgraphs = new LinkedList<>();
            while (mergedSetsIterator.hasNext()) {
                JSONObject set = (JSONObject) mergedSetsIterator.next();
                JSONArray setArray = (JSONArray) set.get("set");
                subgraphs.add((this.gameBoard.getSubgraph(this.getSetCells(setArray))));
            }
            CellSet cellSet = new CellSet(subgraphs, setRules);
            this.game.addSet(cellSet);
        }
    }

    /*
     * Giving a Json Set Array create a Graph of cells
     *
     */

    private Graph createGraph(JSONArray setArray) {
        Graph cellGraph = new Graph();
        ArrayList<Cell> cells = getSetCells(setArray);
        cells.forEach(cellGraph::addCell);
        return cellGraph;
    }


    private Queue<IndexedGraph> createSubgraps(JSONArray setArray) {
/*
        Graph cellGraph = new Graph();
        ArrayList<Cell> cells = getSetCells(setArray);
        cells.forEach(cellGraph::addCell);
        return cellGraph;
*/
        ArrayList<Cell> cells = getSetCells(setArray);
        Queue<IndexedGraph> subgraphs = new LinkedList<>();
        subgraphs.add(this.gameBoard.getSubgraph(cells));
        return subgraphs;
    }

    /*
     * Iterate Json Array
     *
     */
    private void iterateJsonArray(JSONArray array, ParserFunctor functor) {
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject object = (JSONObject) iterator.next();
            functor.parse(object);
        }
    }

    /*
     * Start Parsing structure, rules and
     * linking files
     *
     */
    private void parseContent() {
        this.parseGameStructure();
        this.parseGameRules();
        this.parseLinkingInformation();
    }

    /*
     * Parse Game Structure
     *
     */
    private void parseGameStructure() {
        try {
            gameBoard = new GameBoard(
                    Integer.parseInt((String) jsonStructure.get("width")),
                    Integer.parseInt((String) jsonStructure.get("height")));



            JSONObject structure = (JSONObject) jsonStructure.get("structure");
            iterateJsonArray((JSONArray) structure.get("cells"), new ParserFunctorCell());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Parse Game Rules
     *
     */
    private void parseGameRules() {
        try {
            iterateJsonArray((JSONArray) jsonRules.get("all_sets"), new ParserFunctorRule());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     /*
      * Parse Linking information and generate the linking symbol table
      * and the linking table
      *
      */
    private void parseLinkingInformation() {
        linkingSymbolsTable = new LinkingSymbolsTable();
        iterateJsonArray((JSONArray) jsonLinkingSymbolsTable.get("LinkingSymbols"), new ParserFunctorLinkingSymbol());

        linkingTable = new LinkingTable();
        iterateJsonArray((JSONArray) jsonLinkingTable.get("LinkingTable"), new ParserFunctorLinkingTable());

        game.setLinker(new ConcreteLinker(gameBoard, linkingTable, linkingSymbolsTable));

    }

    /*
     * Parse Linking symbol object and the simbols to linking symbols table
     *
     */
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

    /*
     * Parse Linking table object and add tokens to linking table
     *
     */
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
            values.add((String) object.get(keys[i]));
        }
        return values;
    }
}
