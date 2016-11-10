package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.*;

/**
 * This class represents a graph that can be created after a collection of Cells and a Graph
 * containing links between those Cells. It keeps the collection of Cells and only those links
 * that are between Cells contained in the collection.
 * It's cells can be identified by an index
 */
public class IndexedGraph {

    private ArrayList<Cell> cells;
    private Graph cellLinks;
    private Graph referenceGraph;

    public IndexedGraph(Collection<Cell> subgraphCells, Graph links) {
        this.cells = new ArrayList<>(subgraphCells);
        this.referenceGraph = links;
        this.updateSubGraphLinks();
    }


    public ArrayList<Cell> getCells() {
        return this.cells;
    }

    public int getNotDirectedLinksCount() {
        return this.cellLinks.getNotDirectedLinksCount();
    }

    public Graph getCellLinks() {
        return this.cellLinks;
    }

    public boolean contains(Cell cell) {
        return this.cells.contains(cell);
    }

    public int getIndexOf(Cell cell) {
        return this.cells.indexOf(cell);
    }

    public Cell getCell(int index) {
        if ( (0 <= index) && (index < this.cells.size()) ) {
            return this.cells.get(index);
        }
        return null;

    }

    public int getConnectedSubGraphsCount() {
        return this.getCellLinks().getConnectedSubGraphsCount();
    }

    public int getLooplessCircuitCount() {
        return this.getCellLinks().getLooplessCircuitCount();
    }

    public void updateSubGraphLinks() {
        this.cellLinks = new Graph();
        for (Cell originSubgraphCell : this.cells) {
            for (Cell destinationSubgraphCell: this.cells) {
                if (this.referenceGraph.linkExistsFromOriginToDestination(originSubgraphCell, destinationSubgraphCell)) {
                    this.cellLinks.addDirectedLinkBetween(originSubgraphCell, destinationSubgraphCell);
                }
            }
        }
    }

}
