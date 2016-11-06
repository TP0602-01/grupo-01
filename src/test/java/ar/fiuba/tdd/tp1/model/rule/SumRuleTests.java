package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class SumRuleTests {

    @Test
    public void theCheckMethodMustReturnTrueWhenTheSumIsCorrect() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell("5");
        InputCell c2 = new InputCell("10");
        InputCell c3 = new InputCell("7");

        graph.addNotDirectedLinkBetween(c1, c2);
        graph.addNotDirectedLinkBetween(c2, c3);

        Rule rule = RuleFactory.create("sum", "22");

        assertTrue(rule.check(graph));
    }

    @Test
    public void theCheckMethodMustReturnFalseIfTheSumValueIsDifferentToTheCompareValue() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell("5");
        InputCell c2 = new InputCell("10");
        InputCell c3 = new InputCell("77");

        graph.addNotDirectedLinkBetween(c1, c2);
        graph.addNotDirectedLinkBetween(c2, c3);

        Rule rule = RuleFactory.create("sum", "22");

        assertFalse(rule.check(graph));
    }

    @Test
    public void theCheckMethodMustReturnFalseIfOnCellHaveCeroValue() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell();
        InputCell c2 = new InputCell();

        graph.addNotDirectedLinkBetween(c1, c2);

        Rule rule = RuleFactory.create("sum", "0");

        assertFalse(rule.check(graph));
    }

}
