package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/* */
public class ConnectedGraphsCountRuleTest {

    @Test
    public void testGraphWithOnlyOneConnectedGraphMustReturnOneAsCount() {
        Cell a1 = new InputCell("1");
        Cell a2 = new InputCell("2");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");
        Cell a5 = new InputCell();

        Graph.reset();
        Graph graph = Graph.getSingleInstance();
        graph.addNotDirectedLinkBetween(a1,a3);
        graph.addNotDirectedLinkBetween(a3,a4);

        Rule rule =  RuleFactory.create("conn_graph", "1");

        assertTrue(rule.check(graph));
    }

    @Test
    public void testGraphWithTwoConnectedGraphMustReturnTwoAsCount() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph.reset();
        Graph graph = Graph.getSingleInstance();
        graph.addNotDirectedLinkBetween(a1,a3);
        graph.addNotDirectedLinkBetween(a3,a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2,a5);

        Rule rule =  RuleFactory.create("conn_graph", "2");

        assertTrue(rule.check(graph));
    }

    @Test
    public void testGraphWithThreeConnectedGraphMustReturnThreeAsCount() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph.reset();
        Graph graph = Graph.getSingleInstance();
        graph.addNotDirectedLinkBetween(a1,a3);
        graph.addNotDirectedLinkBetween(a3,a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2,a5);

        Cell a6 = new InputCell("6");
        Cell a7 = new InputCell("7");
        graph.addNotDirectedLinkBetween(a6,a7);

        Rule rule =  RuleFactory.create("conn_graph", "3");

        assertTrue(rule.check(graph));
    }

    @Test
    public void testGraphWithTwoConnectedGraphMustReturnFalseIfItIsTestedWithOne() {
        Cell a1 = new InputCell("1");
        Cell a3 = new InputCell("3");
        Cell a4 = new InputCell("4");

        Graph.reset();
        Graph graph = Graph.getSingleInstance();
        graph.addNotDirectedLinkBetween(a1,a3);
        graph.addNotDirectedLinkBetween(a3,a4);

        Cell a2 = new InputCell("2");
        Cell a5 = new InputCell("5");
        graph.addNotDirectedLinkBetween(a2,a5);

        Rule rule =  RuleFactory.create("conn_graph", "1");

        assertFalse(rule.check(graph));
    }

}
