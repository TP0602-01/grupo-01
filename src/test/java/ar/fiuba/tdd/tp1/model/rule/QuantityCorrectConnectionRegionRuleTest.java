package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.rule.QuantityCorrectConnectionRegionRule;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;


public class QuantityCorrectConnectionRegionRuleTest {
    @Test
    public void QuantityCorrect(){
        Cell a1 = new FixedCell(".");
        Cell a2 = new FixedCell(".");
        Cell a3 = new FixedCell(".");
        Graph graph = new Graph();
        graph.addDirectedLinkBetween(a1,a2);
        graph.addDirectedLinkBetween(a2,a3);
        graph.addDirectedLinkBetween(a1,a3);
        QuantityCorrectConnectionRegionRule rule = new QuantityCorrectConnectionRegionRule(graph);
        rule.setQuantity(4);
        assertTrue(rule.check());
    }
}
