package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 17/10/2016.
 */
public class GraphTests {

    @Test
    public void testAddingADirectedLinkBetweenTwoCellsOnlyCreatesALinkFromTheFirstToTheSecond() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();
        graph.addDirectedLinkBetween(first, second);
        assertTrue(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testAddingANotDirectedLinkBetweenTwoCellsCreatesLinksFromOneToTheOtherOne() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(first, second);
        assertTrue(graph.linkExistsFromOriginToDestination(first, second));
        assertTrue(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testRemovingADirectedLinkBetweenTwoCellsOnlyRemovesTheLinkFromTheFirstToTheSeond() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(first, second);
        graph.removeDirectedLinkBetween(first, second);
        assertFalse(graph.linkExistsFromOriginToDestination(first, second));
        assertTrue(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testRemovingANotDirectedLinkBetweenTwoCellsRemovesLinksFromOneToTheOtherOne() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(first, second);
        graph.removeNotDirectedLinkBetween(first, second);
        assertFalse(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testAddingManyLinksFromOneCellToAnotherOneOnlyCreatesOneLinkFromTheFisrtToTheSecond() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();

        for (int i = 0; i <= 10; i++) {
            graph.addDirectedLinkBetween(first, second);
        }

        assertTrue(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testRemovingADirectedLinkBetweenTwoUnlinkedCellsRaisesNoErrors() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();

        graph.removeDirectedLinkBetween(first, second);

        assertFalse(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }

    @Test
    public void testRemovingANotDirectedLinkBetweenTwoUnlinkedCellsRaisesNoErrors() {
        Cell first = new InputCell("");
        Cell second = new InputCell("");
        Graph graph = new Graph();

        graph.removeNotDirectedLinkBetween(first, second);

        assertFalse(graph.linkExistsFromOriginToDestination(first, second));
        assertFalse(graph.linkExistsFromOriginToDestination(second, first));
    }









}
