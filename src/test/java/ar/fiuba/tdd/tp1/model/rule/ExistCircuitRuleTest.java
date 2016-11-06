package ar.fiuba.tdd.tp1.model.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.ExistCircuitRule;
import junit.framework.TestCase;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ExistCircuitRuleTest extends TestCase {

    @Override
    protected void setUp() {
        Graph.reset();
    }


    @Test
    public void testExistCircuitWhenExistsCircuit() {
        Graph graph = Graph.getSingleInstance();
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell(".");
        Cell a4 = new InputCell(".");

        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);
        graph.addNotDirectedLinkBetween(a4, a1);

        int quantity = 1;
        ExistCircuitRule rule = new ExistCircuitRule(graph, quantity);

        assertTrue(rule.check(graph));
    }

    @Test
    public void testExistCircuitWhenNotExistsCircuit() {
        Graph graph = Graph.getSingleInstance();
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell(".");
        Cell a4 = new InputCell(".");

        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a3, a4);

        int quantity = 1;
        ExistCircuitRule rule = new ExistCircuitRule(graph, quantity);

        assertFalse(rule.check(graph));
    }
}
