package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/* */
public class ConnectedGraphsTests {

    @Test
    public void testGraphWithOnlyOneConnectedGraphMustReturnOneAsCount() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");
        Cell a5 = new InputCell();

        Graph graph = new Graph();

        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        assertEquals(1, graph.getConnectedSubGraphsCount());
    }

    @Test
    public void testGraphWithTwoConnectedGraphMustReturnTwoAsCount() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2, a5);

        assertEquals(2, graph.getConnectedSubGraphsCount());
    }

    @Test
    public void testGraphWithOnlyOneCircuitMustReturnOneAsConnectedGraphCount() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        assertEquals(1, graph.getConnectedSubGraphsCount());
    }

    @Test
    public void testGraphWithOneCircuitAndASeparatedConnectedSubgraphMustReturnTwoAsConnectedGraphCount() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        Cell a5 = new InputCell("5");
        Cell a6 = new InputCell("6");
        graph.addNotDirectedLinkBetween(a5, a6);


        assertEquals(2, graph.getConnectedSubGraphsCount());
    }

    @Test
    public void testGraphWithTwoCircuitsBecomeOneConnectedGraphWhenAddingALinkFromOneToTheOther() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        Cell b5 = new InputCell("5");
        Cell b6 = new InputCell("6");
        Cell b7 = new InputCell("7");
        graph.addNotDirectedLinkBetween(b5, b6);
        graph.addNotDirectedLinkBetween(b6, b7);
        graph.addNotDirectedLinkBetween(b7, b5);
        //Up to here there are 2 connected graphs
        assertEquals(2, graph.getConnectedSubGraphsCount());

        //A link is added from one subGraph to the other
        graph.addNotDirectedLinkBetween(b6, a3);
        //There is only one connected graph now
        assertEquals(1, graph.getConnectedSubGraphsCount());
    }


}
