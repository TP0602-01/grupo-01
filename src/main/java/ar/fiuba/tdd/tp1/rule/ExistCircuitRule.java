package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;

public class ExistCircuitRule {
    Graph mygraph;

    ExistCircuitRule(Graph graph) {
        mygraph = graph;
    }

    boolean check() {
        return mygraph.getCircuitCount();
    }
}
