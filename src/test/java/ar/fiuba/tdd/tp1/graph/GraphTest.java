package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;

import org.junit.Test;

import java.util.Collection;
import java.util.Vector;

import static junit.framework.TestCase.assertTrue;

public class GraphTest {

    @Test
    public void testLinkCorrect() {
        Cell a1 = new FixedCell("a");
        Cell a2 = new FixedCell("b");
        
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1,a2);
        assertTrue(graph.linkExistsFromOriginToDestination(a1,a2));
    }

    @Test
    public void existOneCircuit() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Graph graph = new Graph();
        graph.addDirectedLinkBetween(a1,a2);
        graph.addDirectedLinkBetween(a2,a3);
        graph.addDirectedLinkBetween(a3,a1);
        System.out.println(graph.getCircuitCount());
        assertTrue(graph.getCircuitCount());
    }

    @Test
    public void existTwoCircuit() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Graph graph = new Graph();
        graph.addDirectedLinkBetween(a1,a2);
        graph.addDirectedLinkBetween(a2,a3);
        graph.addDirectedLinkBetween(a3,a1);
        graph.addDirectedLinkBetween(a3,a2);

        assertTrue(graph.getCircuitCount());
    }

    @Test
    public void getCellOfLinks() {
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");
        Cell a3 = new FixedCell("a3");
        Graph graph = new Graph();

        graph.addDirectedLinkBetween(a1,a2);
        graph.addDirectedLinkBetween(a1,a3);
        Collection<Cell> links = graph.getLinks(a1);
        assertTrue(links.contains(a2));
        assertTrue(links.contains(a3));
    }

    @Test
    public void getQuantityCorrectOfCircuit() {
        int cantCellsCircuit = 3;
        int cantCircuits = 10;
        Graph graph = new Graph();
        Vector<Vector<Cell>> circuits = new Vector<>();
        /*Create circuits*/
        for (int j = 0; j < cantCircuits; ++j) {
            Vector<Cell> cells = new Vector<>();
            for (int i = 0; i < cantCellsCircuit; ++i) {
                Cell cell = new FixedCell("");
                cells.add(cell);
            }
            circuits.add(cells);
        }

        for (int j = 0; j < cantCircuits; ++j) {
            Vector<Cell> cells = circuits.elementAt(j);
            Cell a1 = cells.elementAt(0);
            Cell a2 = cells.elementAt(1);
            Cell a3 = cells.elementAt(2);
            graph.addDirectedLinkBetween(a1,a2);
            graph.addDirectedLinkBetween(a2,a3);
            graph.addDirectedLinkBetween(a3,a1);
        }

        assertTrue(graph.getCircuitCount());
    }

    @Test
    public void getQuantityCorrectOfOneCircuit() {
        int cantCellsCircuit = 10;
        Graph graph = new Graph();
        Vector<Cell> cells = new Vector<>();
        /*Create cells*/
        for (int i = 0 ; i < cantCellsCircuit; ++i) {
            Cell cell = new FixedCell("");
            cells.add(cell);
        }

        for (int i = 0 ; i < cantCellsCircuit - 1; ++i) {
            Cell a1 = cells.elementAt(i);
            Cell a2 = cells.elementAt(i + 1);
            graph.addDirectedLinkBetween(a1, a2);
        }
        Cell first = cells.firstElement();
        Cell last = cells.lastElement();
        graph.addDirectedLinkBetween(last, first);

        assertTrue(graph.getCircuitCount());
    }

    @Test
    public void noTExistCircuitGraphEmpty() {
        Graph graph = new Graph();
        assertTrue(!graph.getCircuitCount());
    }

    @Test
    public void noTExistCircuitGraphTwoCells() {
        Graph graph = new Graph();
        Cell a1 = new FixedCell("a1");
        Cell a2 = new FixedCell("a2");

        graph.addDirectedLinkBetween(a1,a2);
        assertTrue(!graph.getCircuitCount());
    }
}
