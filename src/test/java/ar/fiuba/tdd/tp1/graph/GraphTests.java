package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
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


    @Test
    public void testNotDirectedLinksCountInAnEmptyGraphShouldBeZero() {
        Graph graph = new Graph();
        assertEquals(0, graph.getNotDirectedLinksCount());
    }

    @Test
    public void testNotDirectedLinksCountInAGraphWithoutDirectedLinksShouldBeZero() {
        Graph graph = new Graph();
        Cell a1 = new InputCell("");
        Cell a2 = new InputCell("");
        Cell a3 = new InputCell("");

        graph.addDirectedLinkBetween(a1, a2);
        graph.addDirectedLinkBetween(a1, a3);
        graph.addDirectedLinkBetween(a2, a3);

        assertEquals(0, graph.getNotDirectedLinksCount());
    }

    @Test
    public void testNotDirectedLinksCountShouldBeOne() {
        Graph graph = new Graph();
        Cell a1 = new InputCell("");
        Cell a2 = new InputCell("");

        graph.addNotDirectedLinkBetween(a1, a2);
        assertEquals(1, graph.getNotDirectedLinksCount());
    }

    @Test
    public void testNotDirectedLinksCountInACompleteGraphOfThreeShouldBeThree() {
        Graph graph = new Graph();
        Cell a1 = new InputCell("");
        Cell a2 = new InputCell("");
        Cell a3 = new InputCell("");

        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a2, a3);
        assertEquals(3, graph.getNotDirectedLinksCount());
    }

    @Test
    public void testNotDirectedLinksCountInAGraphWithCellsLinkedToThemselves() {
        Graph graph = new Graph();
        Cell a1 = new InputCell("");
        Cell a2 = new InputCell("");
        Cell a3 = new InputCell("");

        graph.addNotDirectedLinkBetween(a1, a1);
        graph.addNotDirectedLinkBetween(a2, a2);
        graph.addNotDirectedLinkBetween(a3, a3);
        assertEquals(3, graph.getNotDirectedLinksCount());
    }


}
