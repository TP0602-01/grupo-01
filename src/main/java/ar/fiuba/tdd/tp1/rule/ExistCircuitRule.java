package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;

public class ExistCircuitRule {
    Graph mygraph;
    //int quantityCheck;

    ExistCircuitRule(Graph graph, int quantity) {
        this.mygraph = graph;
        //this.quantityCheck = quantity;
    }

    boolean check() {
        return mygraph.getCircuitCount();
    }
}
