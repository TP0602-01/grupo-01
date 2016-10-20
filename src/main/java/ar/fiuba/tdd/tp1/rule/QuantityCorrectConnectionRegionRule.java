package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Collection;
import java.util.Vector;

public class QuantityCorrectConnectionRegionRule extends Rule {
    //private Graph myGraph;
    private int quantity;

    public QuantityCorrectConnectionRegionRule() {
        //myGraph = graph;
    }

    public void setQuantity(int qualityConnection) {

        quantity = qualityConnection;
    }
/*
    public boolean check() {
        int sum = 0;
        Collection<Cell> cells = myGraph.getCells();
        for (Cell cell: cells) {
            if (cell.getData().equals("")) {
                sum++;
            }
        }
        return sum == quantity;
    }
*/

    @Override
    public boolean check(Graph graph) {
        int sum = 0;
        Collection<Cell> cells = graph.getCells();
        for (Cell cell : cells) {
            if (cell.getData().equals("")) {
                sum++;
            }
        }
        return sum == quantity;
    }
}



