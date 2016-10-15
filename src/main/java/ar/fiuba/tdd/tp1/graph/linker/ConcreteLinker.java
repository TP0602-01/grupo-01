package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.LinkingSymbolsTable;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linksmanager.LinksManager;
import javafx.util.Pair;

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

    @Override
    public void updateLinkableLinks(int row, int column) {
        //Linkable origin = this.linkableMatrix.getLinkable(row, column);
        Cell origin = this.linkableMatrix.getCell(row, column);

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
    }
}



