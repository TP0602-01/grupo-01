package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkeable;
import ar.fiuba.tdd.tp1.graph.linksManager.LinksManager;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public class ConcreteSquareLinker implements SquareLinker {

    //TODO: se podrian crear objetos para que esto quede mas entendible
    LinkeableMatrix linkeableMatrix;
    LinksManager linksManager;
    LinkingTable linkingTable;




    public ConcreteSquareLinker(LinkeableMatrix linkeableMatrix, LinksManager linksManager){
        this.linkeableMatrix = linkeableMatrix;
        this.linksManager = linksManager;
        this.linkingTable = new LinkingTable();
    }


    @Override
    public void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens) {
        //Setea en que direccion (representados como offsets) se van a chequear los linkeos
        for( String destinationToken: destinationTokens){
            this.linkingTable.addEntry(rowOffset, columnOffset, originTokens, destinationToken);
        }
    }

    @Override
    public void updateLinkeablesLinks(int row, int column) {


        Linkeable origin = this.linkeableMatrix.getLinkeable(row, column);
        Set<String> originLinkingTokens = origin.getLinkingTokens();
        //Recorro cada posible offset de la tabla
        for ( Pair<Integer, Integer> currentOffset: this.linkingTable.getOffsets() ) {
            boolean shouldBeLinked = false;
            int rowOffset = currentOffset.getKey();
            int columnOffset = currentOffset.getValue();

            Linkeable destination = this.linkeableMatrix.getLinkeable(row + rowOffset, column + columnOffset);
            if (destination != null) {
                Set<String> destinationLinkingTokens = destination.getLinkingTokens();

                for (String originToken : originLinkingTokens) {
                    for (String destinationToken : destinationLinkingTokens) {
                        if (this.linkingTable.checkEntryExistance(rowOffset, columnOffset, originToken, destinationToken)) {
                            shouldBeLinked = true;
                        }
                    }
                }
                if (shouldBeLinked) {
                    this.linksManager.addNotDirectedLinkBetween(origin, destination);
                } else {
                    this.linksManager.removeNotDirectedLinkBetween(origin, destination);
                }
            }
        }
    }
}



