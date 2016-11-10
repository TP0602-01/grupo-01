package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.Collection;


/*
 * Quantity Correct Connection Region Rule check that the quantity
 * of input cells are the expected.
 *
 */
public class QuantityCorrectConnectionRegionRule extends SingleGroupRule {
    private int quantity;

    public QuantityCorrectConnectionRegionRule(int expctedSum) {
        this.quantity = expctedSum;
    }

    @Override
    public boolean check(IndexedGraph subGraph) {
        int sum = 0;
        Collection<Cell> cells = subGraph.getCells();
        for (Cell cell : cells) {
            if (!cell.getData().equals("") && !cell.getData().equals("0")) {
                sum++;
            }
        }
        return sum == quantity;
    }
}



