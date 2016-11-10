package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.List;
import java.util.Queue;


/*
 * Abstraction of Rule that receives a collection of subgraphs and apply
 * a rule to then.
 *
 */
public abstract class Rule {
    /* Return True if all graphs are compatible with the Rule */
    public abstract boolean check(Queue<IndexedGraph> subgraphs);
}
