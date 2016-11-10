package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;


/*
 * Abstraction of Rule that receive a set of cells and apply
 * a rule to then.
 *
 */
public abstract class Rule {
    /* Return True if the graph is compatible with the Rule */
    public abstract boolean check(Graph graph);

}
