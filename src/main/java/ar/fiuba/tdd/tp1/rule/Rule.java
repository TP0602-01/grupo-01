package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.List;
import java.util.Queue;


/*
 * Abstraction of Rule that receive a set of cells and apply
 * a rule to then.
 *
 */
public abstract class Rule {
    /* Return True if the graph is compatible with the Rule */
    public abstract boolean check(Graph graph);

    public boolean check(Queue<IndexedGraph> subgraphs) {
        boolean checkResult = true;
        for (IndexedGraph subgraph: subgraphs) {
            Graph fakeGraph = new Graph();
            for (Cell cell: subgraph.getCells()) {
                fakeGraph.addCell(cell);
            }
            checkResult = checkResult && this.check(fakeGraph);
        }
        return checkResult;
    }
}
