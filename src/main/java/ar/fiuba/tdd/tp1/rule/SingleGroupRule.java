package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.rule.Rule;

import java.util.Queue;

/**
 * Created by User on 10/11/2016.
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
