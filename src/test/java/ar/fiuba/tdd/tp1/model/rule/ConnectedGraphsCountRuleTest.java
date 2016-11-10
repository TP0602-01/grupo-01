package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.rule.Rule;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ConnectedGraphsCountRuleTest extends RuleTests{


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

        Rule rule = RuleFactory.create("conn_graph", "1");

        Queue<IndexedGraph> subgraph = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2,a3,a4,a5}, graph);
        assertTrue(rule.check(subgraph));
    }



    @Test
    public void testGraphWithTwoConnectedGraphMustReturnTwoAsCount() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph.reset();
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2, a5);

        Rule rule = RuleFactory.create("conn_graph", "2");

        Queue<IndexedGraph> subgraphs = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2,a3,a4,a5}, graph);
        assertTrue(rule.check(subgraphs));
    }

    @Test
    public void testGraphWithThreeConnectedGraphMustReturnThreeAsCount() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2, a5);

        Cell a6 = new InputCell("6");
        Cell a7 = new InputCell("7");
        graph.addNotDirectedLinkBetween(a6, a7);

        Rule rule = RuleFactory.create("conn_graph", "3");

        Queue<IndexedGraph> subgraphs = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2,a3,a4,a5,a6,a7}, graph);
        assertTrue(rule.check(subgraphs));
    }

    @Test
    public void testGraphWithTwoConnectedGraphMustReturnFalseIfItIsTestedWithOne() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph.reset();
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2, a5);

        Rule rule = RuleFactory.create("conn_graph", "1");

        Queue<IndexedGraph> subgraphs = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2,a3,a4,a5}, graph);
        assertFalse(rule.check(subgraphs));
    }

}
