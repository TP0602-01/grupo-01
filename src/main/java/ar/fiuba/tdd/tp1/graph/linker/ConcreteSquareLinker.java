package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linksmanager.LinksManager;
import javafx.util.Pair;

import java.util.Set;

/* */
public class ConcreteSquareLinker implements SquareLinker {

    //TODO: se podrian crear objetos para que esto quede mas entendible
    private LinkableMatrix linkableMatrix;
    private LinksManager linksManager;
    private LinkingTable linkingTable;

    public ConcreteSquareLinker(LinkableMatrix linkableMatrix, LinksManager linksManager) {
        this.linkableMatrix = linkableMatrix;
        this.linksManager = linksManager;
        this.linkingTable = new LinkingTable();
    }


    @Override
    public void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens) {
        //Setea en que direccion (representados como offsets) se van a chequear los linkeos
        for (String destinationToken: destinationTokens) {
            this.linkingTable.addEntry(rowOffset, columnOffset, originTokens, destinationToken);
        }
    }

    @Override
    public void updateLinkableLinks(int row, int column) {
        Linkable origin = this.linkableMatrix.getLinkeable(row, column);
        Set<String> originLinkingTokens = origin.getLinkingTokens();
        //Recorro cada posible offset de la tabla
        for ( Pair<Integer, Integer> currentOffset: this.linkingTable.getOffsets() ) {
            boolean shouldBeLinked = false;
            int rowOffset = currentOffset.getKey();
            int columnOffset = currentOffset.getValue();

            Linkable destination = this.linkableMatrix.getLinkeable(row + rowOffset, column + columnOffset);
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



