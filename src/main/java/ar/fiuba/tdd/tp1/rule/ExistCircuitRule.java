package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;

public class ExistCircuitRule extends Rule {
    Graph mygraph;
    int quantityCheck;

    public ExistCircuitRule(Graph graph, int quantity) {
        this.mygraph = graph;
        this.quantityCheck = quantity;
    }

    @Override
    public boolean check(Graph graph) {
        return Graph.getSingleInstance().getLooplessCircuitCount() == quantityCheck;
    }
}
