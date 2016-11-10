package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Collection;
import java.util.Vector;


/*
 * Quantity Correct Connection Region Rule check that the quantity
 * of input cells are the expected.
 *
 */
public class QuantityCorrectConnectionRegionRule extends Rule {
    private int quantity;

    public QuantityCorrectConnectionRegionRule(int expctedSum) {
        this.quantity = expctedSum;
    }

    @Override
    public boolean check(Graph graph) {
        int sum = 0;
        Collection<Cell> cells = graph.getCells();
        for (Cell cell : cells) {
            if (!cell.getData().equals("") && !cell.getData().equals("0")) {
                sum++;
            }
        }
        return sum == quantity;
    }
}



