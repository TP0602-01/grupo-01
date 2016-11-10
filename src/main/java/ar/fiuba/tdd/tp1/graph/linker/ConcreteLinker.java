package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.LinkingSymbolsTable;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linksmanager.LinksManager;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * ConcreteLinker has the responsibility of comparing the data of different Cells and link them
 * if their data is compatible.
 * This implementation adds not directed links between Cells
 */
public class ConcreteLinker implements Linker {

    //private LinkableMatrix linkableMatrix;
    private GameBoard linkableMatrix;
    private Graph graph;
    private LinkingTable linkingTable;
    private LinkingSymbolsTable linkingSymbols;



    public ConcreteLinker(GameBoard gameBoard, LinkingTable linkingTable) {
        this.linkableMatrix = gameBoard;
        this.graph = Graph.getSingleInstance();
        this.linkingTable = linkingTable;
    }

    public ConcreteLinker(GameBoard gameBoard, LinkingTable linkingTable, LinkingSymbolsTable linkingSymbols) {
        this.linkableMatrix = gameBoard;

        this.graph = gameBoard.getCellsLinks();
        //TEMPORAL PARA QUE NO EXPLOTEN CIERTAS PRUEBAS:
        //TODO: SACARLO
        Graph.setSingleInstance(this.graph);

        this.linkingTable = linkingTable;
        this.linkingSymbols = linkingSymbols;
    }


    private boolean originAndDestinationTokensImplyThatTheyMustBeLinked(Pair<Integer, Integer> offset,
                                                                        String originToken, String destinationToken) {
        Integer rowOffset = offset.getKey();
        Integer columnOffset = offset.getValue();
        return this.linkingTable.checkEntryExistance(rowOffset, columnOffset, originToken, destinationToken);
    }


    private void linkIfItsPossible(Cell origin, Cell destination, boolean shouldBeLinked) {
        if (shouldBeLinked) {
            this.graph.addNotDirectedLinkBetween(origin, destination);
        } else {
            this.graph.removeNotDirectedLinkBetween(origin, destination);
        }
    }


    private void checkTokensAndlinkIfItsPossible(Cell origin, Cell destination, Pair<Integer, Integer> offset) {
        if (destination == null) {
            return;
        }
        Set<String> originLinkingTokens = this.linkingSymbols.getLinkingTokensFor(origin.getLinkingSymbol());
        Set<String> destinationLinkingTokens = this.linkingSymbols.getLinkingTokensFor(destination.getLinkingSymbol());
        boolean shouldBeLinked = false;
        for (String originToken : originLinkingTokens) {
            for (String destinationToken : destinationLinkingTokens) {
                shouldBeLinked = shouldBeLinked
                        | originAndDestinationTokensImplyThatTheyMustBeLinked(offset, originToken, destinationToken);
            }
        }
        linkIfItsPossible(origin, destination, shouldBeLinked);
    }

    private Map<Cell, Pair<Integer, Integer>> getCellNeigbors(int originRow, int originColumn) {
        Map<Cell, Pair<Integer, Integer>> result = new HashMap<>();
        Set<Pair<Integer, Integer>> posibleOffsets = this.linkingTable.getOffsets();

        for (Pair<Integer, Integer> currentOffset : posibleOffsets) {
            int rowOffset = currentOffset.getKey();
            int columnOffset = currentOffset.getValue();
            Cell destination = this.linkableMatrix.getCell(originRow + rowOffset, originColumn + columnOffset);
            result.put(destination, currentOffset);
        }

        return result;
    }


    @Override
    public void updateLinkableLinks(int row, int column) {
        //Linkable origin = this.linkableMatrix.getLinkable(row, column);
        Cell origin = this.linkableMatrix.getCell(row, column);

        Map<Cell, Pair<Integer, Integer>> neighbors = this.getCellNeigbors(row, column);


        Iterator it = neighbors.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Cell cell = (Cell) pair.getKey();
            this.checkTokensAndlinkIfItsPossible(origin, cell, neighbors.get(cell));
            it.remove();

        }

    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

}



