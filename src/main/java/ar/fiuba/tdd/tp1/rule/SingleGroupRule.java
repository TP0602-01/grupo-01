package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.rule.Rule;

import java.util.Queue;

/**
 * SingleGroupRule only checks one subgraph at a time and returns true only if the
 * rule is correct in all of the subgrphs.
 */
public abstract class SingleGroupRule extends Rule{

    public boolean check(Queue<IndexedGraph> subgraphs) {
        boolean checkResult = true;
        for (IndexedGraph subgraph : subgraphs) {
            checkResult = checkResult && this.check(subgraph);
        }
        return checkResult;
    }

    public abstract boolean check(IndexedGraph subGraph);
}
