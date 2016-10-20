package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;

public class ExistCircuitRule extends Rule {
    Graph mygraph;
    int quantityCheck;

    public ExistCircuitRule(Graph graph, int quantity) {
        this.mygraph = graph;
        this.quantityCheck = quantity;
    }

    boolean check() {
        //TODO: VER ESTO SI SE CAMBIA A DEVOLVER LA CANTIDAD DE CIRCUITOS
        if ((this.quantityCheck == 0) && (!mygraph.getCircuitCount())) {
            return false;
        } else if ((this.quantityCheck >= 1) && (mygraph.getCircuitCount())) {
            return true;
        }
        return mygraph.getCircuitCount();

    }

    @Override
    public boolean check(Graph graph) {
        return graph.getLooplessCircuitCount() == quantityCheck;
    }
}
