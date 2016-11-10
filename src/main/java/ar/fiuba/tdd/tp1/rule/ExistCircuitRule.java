package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;

/*
 * Exist Circuit Rule check that the graph given by parameter
 * has an expected quantity loopless circuits
 *
 */
public class ExistCircuitRule extends Rule {
    int quantityCheck;

    public ExistCircuitRule(Graph graph, int quantity) {
        this.quantityCheck = quantity;
    }

    @Override
    public boolean check(Graph graph) {
        return Graph.getSingleInstance().getLooplessCircuitCount() == quantityCheck;
    }
}