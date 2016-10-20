package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteLinker;
import ar.fiuba.tdd.tp1.graph.linker.Linker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/* */
public class LinkedGraphTests {

    LinkingSymbolsTable linkingSymbolsTable;
    LinkingTable linkingTable;

    public LinkedGraphTests() {
        this.linkingSymbolsTable = new LinkingSymbolsTable();
        this.setInTableLinkingTokensForSymbol(this.linkingSymbolsTable, "-->", new String[] {"RIGHT"});
        this.setInTableLinkingTokensForSymbol(this.linkingSymbolsTable, "<--", new String[] {"LEFT"});
        this.setInTableLinkingTokensForSymbol(this.linkingSymbolsTable, "v", new String[] {"DOWN"});
        this.setInTableLinkingTokensForSymbol(this.linkingSymbolsTable, "^", new String[] {"UP"});

        this.linkingTable = new LinkingTable();
        this.linkingTable.addEntry(0, 1, "RIGHT", "LEFT");
        this.linkingTable.addEntry(0, -1, "LEFT", "RIGHT");
        this.linkingTable.addEntry(-1, 0, "UP", "DOWN");
        this.linkingTable.addEntry(1, 0, "DOWN", "UP");
    }

    private GameBoard createGameBoard(int rows, int columns) {
        GameBoard board = new GameBoard(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                board.addCell(row, col, new InputCell());
            }
        }
        return board;
    }

    private void setInTableLinkingTokensForSymbol(LinkingSymbolsTable table, String symbol, String[] tokens) {
        Set<String> linkingTokens = new HashSet<>();
        for (int i = 0; i < tokens.length; i++) {
            linkingTokens.add(tokens[i]);
        }
        table.setSymbolsLinkingTokens(symbol, linkingTokens);
    }

    @Test
    public void testAddDataToTwoCellsAndLinkThemByUpdatongTheirLinks() {
        GameBoard gameBoard = this.createGameBoard(3, 3);

        Linker linker = new ConcreteLinker(gameBoard, this.linkingTable, this.linkingSymbolsTable);

        Cell first = gameBoard.getCell(0, 0);
        Cell second = gameBoard.getCell(0, 1);

        first.setData("-->");
        second.setData("<--");

        linker.updateLinkableLinks(0, 0);
        Graph graph = linker.getGraph();

        assertTrue(graph.linkExistsFromOriginToDestination(first, second));
        assertTrue(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testAddingDataOnlyInOneCellsAndUpdatingItsLinksLeavesItWithNoLinks() {
        GameBoard gameBoard = this.createGameBoard(3, 3);

        Linker linker = new ConcreteLinker(gameBoard, this.linkingTable, this.linkingSymbolsTable);

        Cell origin = gameBoard.getCell(0, 0);
        origin.setData("-->");
        linker.updateLinkableLinks(0, 0);
        Graph graph = linker.getGraph();

        Cell rightCell = gameBoard.getCell(0, 1);
        Cell downCell = gameBoard.getCell(1, 0);
        Cell downRightCell = gameBoard.getCell(1, 1);
        assertFalse(graph.linkExistsFromOriginToDestination(origin, rightCell));
        assertFalse(graph.linkExistsFromOriginToDestination(origin, downCell));
        assertFalse(graph.linkExistsFromOriginToDestination(origin, downRightCell));
    }

    @Test
    public void testChangingOneLinkedCellsDataToAnotherRemovesItsLinksCreatedUsingItsOriginalData() {
        GameBoard gameBoard = this.createGameBoard(3, 3);

        Linker linker = new ConcreteLinker(gameBoard, this.linkingTable, this.linkingSymbolsTable);
        Cell first = gameBoard.getCell(0, 0);
        Cell second = gameBoard.getCell(0, 1);
        first.setData("-->");
        second.setData("<--");
        linker.updateLinkableLinks(0, 0);

        //Data is changed
        first.setData("<--");
        linker.updateLinkableLinks(0, 0);
        Graph graph = linker.getGraph();

        assertFalse(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }
//
//    private void createLinkingEntryWithOnlyOneDestinationLinkingToken(
//            Linker linker, int rowOffset, int columnOffset,
//            String originToken, String destinationToken) {
//        Set<String> destinationTokens = new HashSet<>();
//        destinationTokens.add(destinationToken);
//        linker.setLinkingInfo(rowOffset, columnOffset, originToken, destinationTokens);
//    }
//
//    private void createLinkingEntryWithNDestinationLinkingTokens(
//            Linker linker,
//            int rowOffset,
//            int columnOffset,
//            String originToken,
//            String[] destinationToken,
//            int destinationTokensCount) {
//        Set<String> destinationTokens = new HashSet<>();
//        for (int i = 0; i < destinationTokensCount; i++) {
//            destinationTokens.add(destinationToken[i]);
//        }
//        linker.setLinkingInfo(rowOffset, columnOffset, originToken, destinationTokens);
//    }
//
//    @Test
//    public void testGokigenNanameConfiguration() {
//
//        GameBoard board = new GameBoard(3, 3);
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                board.addCell(row, col, new InputCell());
//            }
//        }
//
//        LinksManager linksManager = new MapLinkManager();
//        //Linker linker = new ConcreteLinker(board, linksManager);
//        //TODO arreglar las pruebas porque cambiaron los constructores
//        Linker linker = new ConcreteLinker(board, null);
//
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "/", "\\");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 1, "/", "/");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "/", "\\");
//        //"/", can't be linked in -1, -1 direction
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "/", "\\");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, -1, "/", "/");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "/", "\\");
//        //"/", can't be linked in 1, 1 direction
//
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "\\", "/");
//        //"\", can't be linked in -1, 1 direction
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "\\", "/");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, -1, "\\", "\\");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "\\", "/");
//        //"\", can't be linked in 1, -1 direction
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "\\", "/");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 1, "\\", "\\");
//
//        /*     0 1 2
//            0 | | | |
//            1 | | | |
//            2 | | | |
//         */
//        Cell a00 = board.getCell(0, 0);
//        Cell a01 = board.getCell(0, 1);
//        Cell a02 = board.getCell(0, 2);
//        Cell a10 = board.getCell(1, 0);
//        Cell a11 = board.getCell(1, 1);
//        Cell a12 = board.getCell(1, 2);
//        Cell a20 = board.getCell(2, 0);
//        Cell a21 = board.getCell(2, 1);
//        Cell a22 = board.getCell(2, 2);
//
//        a00.addLinkingToken("/");
//        //a01 has no linking tokens
//        a02.addLinkingToken("/");
//        a10.addLinkingToken("\\");
//        a11.addLinkingToken("/");
//        a12.addLinkingToken("\\");
//        //a20 has no linking tokens
//        a21.addLinkingToken("\\");
//        a22.addLinkingToken("\\");
//
//        /*     0 1 2
//            0 |/| |/|
//            1 |\|/|\|
//            2 | |\|\|
//        */
//
//        linker.updateLinkableLinks(1, 1);
//
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a00));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a01));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a20));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a22));
//
//        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a02));
//        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a10));
//        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a12));
//        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a21));
//    }
//
//
//    @Test
//    public void testCountryRoadConfiguration() {
//        GameBoard board = new GameBoard(3, 3);
//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                board.addCell(row, col, new InputCell());
//            }
//        }
//
//        LinksManager linksManager = new MapLinkManager();
//        //Linker linker = new ConcreteLinker(board, linksManager);
//        //TODO: REHACER LAS PRUEBAS PORQUE CAMBIARON CONSTRUCTORES Y METODOS PUBLICOS
//        Linker linker = new ConcreteLinker(board, null);
//
//        //4 lineas de configuracion por un lado
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "R", "L");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "U", "D");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "L", "R");
//        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "D", "U");
//
//        /*     0 1 2
//            0 | | | |
//            1 | | | |
//            2 | | | |
//         */
//        Cell a00 = board.getCell(0, 0);
//        Cell a01 = board.getCell(0, 1);
//        Cell a02 = board.getCell(0, 2);
//        Cell a10 = board.getCell(1, 0);
//        Cell a11 = board.getCell(1, 1);
//        Cell a12 = board.getCell(1, 2);
//        Cell a20 = board.getCell(2, 0);
//        Cell a21 = board.getCell(2, 1);
//        Cell a22 = board.getCell(2, 2);
//
//
//        /*     0         1     2
//            0 ||     ||  |  ||  ¡--||
//            1 || --- ||  ¡--||--!  ||
//            2 ||     ||     ||     ||
//         */
//
//
//        //Deberia haber una especie de traductor de input a linkingTokens
//        //Por ejemplo "¡--" ----> ["D", "R"]
//
//        a01.addLinkingToken("U");
//        a01.addLinkingToken("D");
//
//        a02.addLinkingToken("D");
//        a02.addLinkingToken("R");
//
//        a10.addLinkingToken("R");
//        a10.addLinkingToken("L");
//
//        a11.addLinkingToken("R");
//        a11.addLinkingToken("D");
//
//        a12.addLinkingToken("L");
//        a12.addLinkingToken("U");
//
//        a21.addLinkingToken("R");
//        a21.addLinkingToken("D");
//
//
//        /*       0       1      2
//            0 ||     ||  |  ||  ¡--||
//            1 || --- ||  ¡--||--!  ||
//            2 ||     ||  ¡--||     ||
//         */
//
//        linker.updateLinkableLinks(1, 1);
//
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a00));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a01));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a02));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a10));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a20));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a21));
//        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a22));
//
//        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a12));
//    }
}
