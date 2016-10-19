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

public class MultRuleTests {

    @Test
    public void theCheckMethodMustReturnTrueWhenTheMultIsCorrect() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell("4");
        InputCell c2 = new InputCell("2");
        InputCell c3 = new InputCell("3");

        graph.addNotDirectedLinkBetween(c1, c2);
        graph.addNotDirectedLinkBetween(c2, c3);

        Rule rule =  RuleFactory.create("mult", "24");

        assertTrue(rule.check(graph));
    }

    @Test
    public void theCheckMethodMustReturnFalseIfTheMultValueIsDifferentToTheCompareValue() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell("4");
        InputCell c2 = new InputCell("2");
        InputCell c3 = new InputCell("3");

        graph.addNotDirectedLinkBetween(c1, c2);
        graph.addNotDirectedLinkBetween(c2, c3);

        Rule rule =  RuleFactory.create("mult", "22");

        assertFalse(rule.check(graph));
    }

    @Test
    public void theCheckMethodMustReturnFalseIfOnCellHaveCeroValue() {
        Graph graph = new Graph();
        InputCell c1 = new InputCell("0");
        InputCell c2 = new InputCell("2");
        InputCell c3 = new InputCell("3");

        graph.addNotDirectedLinkBetween(c1, c2);
        graph.addNotDirectedLinkBetween(c2, c3);

        Rule rule =  RuleFactory.create("mult", "0");

        assertFalse(rule.check(graph));
    }

}
