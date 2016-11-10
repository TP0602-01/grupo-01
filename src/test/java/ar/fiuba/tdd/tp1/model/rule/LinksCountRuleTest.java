package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.rule.LinksCountRule;
import ar.fiuba.tdd.tp1.rule.QuantityCorrectConnectionRegionRule;
import org.junit.Test;

import java.util.Queue;

import static junit.framework.TestCase.assertTrue;


public class LinksCountRuleTest extends RuleTests {

    @Test
    public void testCountLinksBetweenTwoLinkedCellsMustBeOne() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");

        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);

        LinksCountRule rule = new LinksCountRule(1);
        Queue<IndexedGraph> subgraph = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2}, graph);
        assertTrue(rule.check(subgraph));
    }

    @Test
    public void testCountLinksBetweenTwoUnlinkedCellsMustBeZero() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");

        Graph graph = new Graph();

        LinksCountRule rule = new LinksCountRule(0);
        Queue<IndexedGraph> subgraph = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2}, graph);
        assertTrue(rule.check(subgraph));
    }

    @Test
    public void testCountLinksBetweenFourCellsMustBeThree() {
        Cell a1 = new InputCell(".");
        Cell a2 = new InputCell(".");
        Cell a3 = new InputCell(".");
        Cell a4 = new InputCell(".");


        Graph graph = new Graph();
        graph.addNotDirectedLinkBetween(a1, a2);
        graph.addNotDirectedLinkBetween(a1, a3);
        graph.addNotDirectedLinkBetween(a4, a3);

        LinksCountRule rule = new LinksCountRule(3);
        Queue<IndexedGraph> subgraph = this.createIndexedGraphsQueueOfOne(new Cell[]{a1,a2,a3,a4}, graph);
        assertTrue(rule.check(subgraph));
    }

}
