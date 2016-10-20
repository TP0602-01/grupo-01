package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Vector;

/**
 */
public class ExistCircuitRule {
    Graph mygraph;
    int quantityCheck;
    ExistCircuitRule(Graph graph, int quantity){
        mygraph = graph;
        quantityCheck = quantity;
    }
    boolean check(){
        return mygraph.getCircuitCount();
    }
}
