package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.*;

/**
 * Created by User on 06/11/2016.
 */
public class IndexedGraph {

    private ArrayList<Cell> cells;
    private Graph cellLinks;

    public IndexedGraph(Collection<Cell> subgraphCells, Graph links) {
        this.cells = new ArrayList<>(subgraphCells);
        this.cellLinks = new Graph();
        for (Cell originSubgraphCell : subgraphCells) {
            for (Cell destinationSubgraphCell: subgraphCells) {
                if (links.linkExistsFromOriginToDestination(originSubgraphCell, destinationSubgraphCell)) {
                    this.cellLinks.addDirectedLinkBetween(originSubgraphCell, destinationSubgraphCell);
                }
            }
        }
    }

    public ArrayList<Cell> getCells() {
        return this.cells;
    }

    public int getNotDirectedLinksCount() {
        return this.cellLinks.getNotDirectedLinksCount();
    }
}