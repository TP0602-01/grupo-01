package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;

import javax.management.openmbean.ArrayType;
import java.sql.Array;
import java.util.*;

/**
 * Created by User on 06/11/2016.
 */
public class IndexedGraph {

    private ArrayList<Cell> cells;
    private Graph cellLinks;

    public IndexedGraph(Collection<Cell> subgraphCells, Graph links) {
        this.cells = new ArrayList<Cell>(subgraphCells);

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

//
//    /* Add Link beetween Cell origin and destination */
//    public void addDirectedLinkBetween(Cell origin, Cell destination) {
//        this.cellLinks.addDirectedLinkBetween(origin, destination);
//    }
//
//    /* Add Link beetween Cell first and second and
//     * Link beetween Cell second and first */
//    public void addNotDirectedLinkBetween(Cell first, Cell second) {
//        this.addDirectedLinkBetween(first, second);
//        this.addDirectedLinkBetween(second, first);
//    }
//
//    /* Remove Link beetween origin and dest */
//    public void removeDirectedLinkBetween(Cell origin, Cell destination) {
//        this.cellLinks.removeNotDirectedLinkBetween(origin, destination);
//    }
//
//    /* Remove Link beetween origin and dest and
//    *  beetween dest and origin */
//    public void removeNotDirectedLinkBetween(Cell first, Cell second) {
//        this.removeDirectedLinkBetween(first, second);
//        this.removeDirectedLinkBetween(second, first);
//    }
}
