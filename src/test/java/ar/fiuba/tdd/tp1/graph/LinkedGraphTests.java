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
}
