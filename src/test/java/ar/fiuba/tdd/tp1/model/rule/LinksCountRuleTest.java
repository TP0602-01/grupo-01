package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.LinksCountRule;
import ar.fiuba.tdd.tp1.rule.QuantityCorrectConnectionRegionRule;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;


public class LinksCountRuleTest {

    @Test
    public void testCountLinksBetweenTwoLinkedCellsMustBeOne() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");

        Graph graph = new Graph();
        graph.addCell(a1);
        graph.addCell(a2);

        Graph.getSingleInstance().addNotDirectedLinkBetween(a1, a2);

        LinksCountRule rule = new LinksCountRule(1);
        assertTrue(rule.check(graph));
    }

    @Test
    public void testCountLinksBetweenTwoUnlinkedCellsMustBeZero() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");

        Graph graph = new Graph();
        graph.addCell(a1);
        graph.addCell(a2);

        LinksCountRule rule = new LinksCountRule(0);
        assertTrue(rule.check(graph));
    }

    @Test
    public void testCountLinksBetweenFourCellsMustBeThree() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell(".");
        Cell a4 = new InputCell(".");

        Graph graph = new Graph();
        graph.addCell(a1);
        graph.addCell(a2);
        graph.addCell(a3);
        graph.addCell(a4);

        Graph.getSingleInstance().addNotDirectedLinkBetween(a1, a2);
        Graph.getSingleInstance().addNotDirectedLinkBetween(a1, a3);
        Graph.getSingleInstance().addNotDirectedLinkBetween(a4, a3);

        LinksCountRule rule = new LinksCountRule(3);
        assertTrue(rule.check(graph));
    }

}
