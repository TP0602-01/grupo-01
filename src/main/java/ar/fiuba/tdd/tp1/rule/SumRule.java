package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.set.Graph;

import java.util.Collection;

/*  */
public class SumRule extends Rule {

    private int expectedSumResult;

    public SumRule(Integer sumResult) {
        this.expectedSumResult = sumResult;
    }

    @Override
    public boolean check(Graph graph) { //TODO: ESTO TIENE TODA LA PINTA DE UN TEMPLATE METHOD
        Collection<Cell> cells = graph.getCells();

        Integer sum = 0;
        for (Cell cell : cells) {
            sum += Integer.parseInt(cell.getData());
        }

        return sum.equals(expectedSumResult);
    }

    //TODO: if this implementation is chosen then replace check() with this method
    /*public boolean directionalCheck() {
        Integer sum = 0;
        //TODO: initialCell might or might NOT be an InputCell
        Cell currentCell = this.initialCell;
        while ( currentCell != null ) {
            sum += currentCell.getData();
            currentCell = this.direction.getNextCell(currentCell);
        }
        return sum.equals(this.expectedSumResult);
    }*/
}
