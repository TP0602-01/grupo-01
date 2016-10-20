package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.graph.Graph;

import ar.fiuba.tdd.tp1.rule.QuantityCorrectConnectionRegionRule;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class QuantityCorrectConnectionRegionRuleTest {

    @Test
    public void testQuantityCorrectConnectionRegionRuleChecksHowManyCellsHasNotNullDataAndItExpectsThree() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell(".");
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a1, a3);
        QuantityCorrectConnectionRegionRule rule = new QuantityCorrectConnectionRegionRule(3);
        assertTrue(rule.check(graph));
    }

    @Test
    public void testQuantityCorrectConnectionRegionRuleChecksReturnsFalseWhenTheNumberOfNotNullCellsIsNotExpected() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell("");
        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a2, a3);
        graph.addNotDirectedLinkBetween(a1, a3);
        QuantityCorrectConnectionRegionRule rule = new QuantityCorrectConnectionRegionRule(3);
        assertFalse(rule.check(graph));
    }
}
