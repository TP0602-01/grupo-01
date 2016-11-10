package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class GraphCircuitsTests {

    @Test
    public void testGraphWithOnlyOneLooplessCircuitMustReturnOneAsCount() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Cell a5 = new InputCell();

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        assertEquals(1, graph.getLooplessCircuitCount());
    }

    @Test
    public void testGraphWithTwoLooplessCircuitMustReturnTwoAsCount() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a1);

        Cell b1 = new FixedCell("b1");
        Cell b2 = new FixedCell("b2");
        Cell b3 = new FixedCell("b3");
        graph.addNotDirectedLinkBetween(b1, b2);
        graph.addNotDirectedLinkBetween(b2, b3);
        graph.addNotDirectedLinkBetween(b3, b1);

        assertEquals(2, graph.getLooplessCircuitCount());
    }

    @Test
    public void testGraphWithOneLooplessCircuitAndOneNonCircuitSubgraphReturnsOneAsLooplessCircuitCount() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a1);
        //a* links forms a circuit

        Cell b1 = new FixedCell("b1");
        Cell b2 = new FixedCell("b2");
        Cell b3 = new FixedCell("b3");
        graph.addNotDirectedLinkBetween(b1, b2);
        graph.addNotDirectedLinkBetween(b2, b3);
        //b* links forms a line b1<-->b2<-->b3

        assertEquals(1, graph.getLooplessCircuitCount());
    }

    @Test
    public void testGraphOnlyOneNonCircuitSubgraphReturnsZeroAsLooplessCircuitCount() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Cell a4 = new FixedCell("a4");
        Cell a5 = new FixedCell("a5");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a1, a5);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a2, a4);

        assertEquals(0, graph.getLooplessCircuitCount());
    }


    @Test
    public void testASubgraphWithAnInternalCircuitButNotACircuitAsAWholeIsNotCountedAsALooplessCircuit() {

        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Cell a4 = new FixedCell("a4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);
        //Internal circuit

        Cell a5 = new FixedCell("a5");
        graph.addNotDirectedLinkBetween(a2, a5);
        //Now it is NOT a circuit as a whole

        assertEquals(0, graph.getLooplessCircuitCount());
    }

    @Test
    public void testAnEulerianCircuitWhithALoopIsNotCountedAsALooplessCircuit() {

        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Cell a4 = new FixedCell("a4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        Cell a5 = new FixedCell("a5");
        Cell a6 = new FixedCell("a6");
        Cell a7 = new FixedCell("a7");

        graph.addNotDirectedLinkBetween(a4, a5);
        graph.addNotDirectedLinkBetween(a5, a6);
        graph.addNotDirectedLinkBetween(a6, a7);
        graph.addNotDirectedLinkBetween(a7, a4);
        /* This case is simulated:
              _
             |_|_
               |_|
         */

        assertEquals(0, graph.getLooplessCircuitCount());
    }

}
