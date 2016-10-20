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

/* */
public class ConcreteLinker implements Linker {

    //TODO: se podrian crear objetos para que esto quede mas entendible
    //private LinkableMatrix linkableMatrix;
    private GameBoard linkableMatrix;
    //private LinksManager linksManager;
    private Graph graph;
    private LinkingTable linkingTable;
    private LinkingSymbolsTable linkingSymbols;

    /*
    public ConcreteLinker(LinkableMatrix linkableMatrix, LinksManager linksManager) {
        this.linkableMatrix = linkableMatrix;
        this.linksManager = linksManager;
        this.linkingTable = new LinkingTable();
    }
    */


    public ConcreteLinker(GameBoard gameBoard, LinkingTable linkingTable) {
        this.linkableMatrix = gameBoard;
        this.graph = new Graph();
        this.linkingTable = linkingTable;
    }

    public ConcreteLinker(GameBoard gameBoard, LinkingTable linkingTable, LinkingSymbolsTable linkingSymbols) {
        this.linkableMatrix = gameBoard;
        this.graph = new Graph();
        this.linkingTable = linkingTable;
        this.linkingSymbols = linkingSymbols;
    }


    @Override
    public void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens) {
        /*
        //Setea en que direccion (representados como offsets) se van a chequear los linkeos
        for (String destinationToken : destinationTokens) {
            this.linkingTable.addEntry(rowOffset, columnOffset, originTokens, destinationToken);
        }
        */
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
            System.out.println("SE LINKEAN");
        } else {
            this.graph.removeNotDirectedLinkBetween(origin, destination);
            System.out.println("NO SE LINKEAN");
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

        for (Cell neighbor : neighbors.keySet()) {
            this.checkTokensAndlinkIfItsPossible(origin, neighbor, neighbors.get(neighbor));
        }

        // TODO: VER SI SE PUEDE REEMPLAZAR O SI FALLA Y TENEMOS QUE VOLVER A LO COMENTADO
        /*
        //Set<String> originLinkingTokens = origin.getLinkingTokens();
        Set<String> originLinkingTokens = this.linkingSymbols.getLinkingTokensFor(origin.getLinkingSymbol());
        //Recorro cada posible offset de la tabla
        for (Pair<Integer, Integer> currentOffset : this.linkingTable.getOffsets()) {
            boolean shouldBeLinked = false;
            int rowOffset = currentOffset.getKey();
            int columnOffset = currentOffset.getValue();

            //Linkable destination = this.linkableMatrix.getLinkable(row + rowOffset, column + columnOffset);
            Cell destination = this.linkableMatrix.getCell(row + rowOffset, column + columnOffset);
            if (destination != null) {
                //Set<String> destinationLinkingTokens = destination.getLinkingTokens();
                Set<String> destinationLinkingTokens = this.linkingSymbols.getLinkingTokensFor(destination.getLinkingSymbol());
                for (String originToken : originLinkingTokens) {
                    for (String destinationToken : destinationLinkingTokens) {
                        if (this.linkingTable.checkEntryExistance(rowOffset, columnOffset, originToken, destinationToken)) {
                            shouldBeLinked = true;
                        }
                    }
                }
                if (shouldBeLinked) {
                    //this.linksManager.addNotDirectedLinkBetween(origin, destination);
                    this.graph.addNotDirectedLinkBetween(origin, destination);
                    System.out.println("SE LINKEAN");
                } else {
                    //this.linksManager.removeNotDirectedLinkBetween(origin, destination);
                    this.graph.removeNotDirectedLinkBetween(origin, destination);
                    System.out.println("NO SE LINKEAN");
                }
            }
        }
        */
    }

    @Override
    public Graph getGraph() {
        return this.graph;
    }

}



