package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 06/11/2016.
 */
public class SubgraphTests {

    @Test
    public void testIndexedGraphIndexesCellsInTheSameOrderOfThePassedCollection() {
        Cell a0 = new InputCell("");
        Cell a1 = new InputCell("");
        Cell a2 = new InputCell("");

        Collection<Cell> cells = new HashSet<>();
        cells.add(a2);
        cells.add(a0);
        cells.add(a1);

        IndexedGraph indexedGraph = new IndexedGraph(cells, new Graph());
        Collection<Cell> indexedGraphCells = indexedGraph.getCells();

        Iterator<Cell> expectedCell = cells.iterator();
        Iterator<Cell> actualCell = indexedGraphCells.iterator();

        int index = 0;
        while (index < 3) {
            assertEquals(expectedCell.next(), actualCell.next());
            index++;
        }
    }

    @Test
    public void testGettingASubgraphShouldReturnAGraphContainningOnlyThoseLinksBetweenPassedCells() {
        GameBoard matrixGraph = new GameBoard(3,3);

        Cell a0 = matrixGraph.getCell(0,0);
        Cell a1 = matrixGraph.getCell(0,1);
        Cell a2 = matrixGraph.getCell(0,2);
        Cell b0 = matrixGraph.getCell(1,0);

        Graph originalCellsLinks = matrixGraph.getCellsLinks();
        originalCellsLinks.addNotDirectedLinkBetween(a0,a1);
        originalCellsLinks.addNotDirectedLinkBetween(a1,a2);
        originalCellsLinks.addNotDirectedLinkBetween(a0,b0);

        Collection<Cell> subgraphCells = new LinkedList<Cell>();
        subgraphCells.add(a0);
        subgraphCells.add(a1);
        subgraphCells.add(a2);

        IndexedGraph subgraph = matrixGraph.getSubgraph(subgraphCells);

        //So many asserts I know...
        assertEquals(3, subgraph.getCells().size());
        assertEquals(2, subgraph.getNotDirectedLinksCount());
        assertTrue(checkCellsInSubgraph(subgraph,subgraphCells));
        assertFalse(subgraph.getCells().contains(b0));
    }

    private boolean checkCellsInSubgraph(IndexedGraph subgraph, Collection<Cell> expectedCells) {
        Collection<Cell> cellsInSubgraph = subgraph.getCells();

        boolean allExpectedCellsAreInSubgraph = true;

        for (Cell expectedCell: expectedCells ) {
            allExpectedCellsAreInSubgraph = allExpectedCellsAreInSubgraph && cellsInSubgraph.contains(expectedCell);
        }
        return allExpectedCellsAreInSubgraph;
    }

}
