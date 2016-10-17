package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Vector;

/**
 */
public class ExistCircuitRule {
    Graph mygraph;
    ExistCircuitRule(Graph graph){
        mygraph = graph;
    }
    boolean check(int quantity){
        return quantity == mygraph.getCircuitCount();
    }
}
