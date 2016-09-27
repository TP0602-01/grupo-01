package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.direction.Direction;

import java.util.Collection;

/*  */
public class SumRule extends BaseRule {

    private Integer expectedSumResult;

    SumRule(Collection<Cell> cells, Integer sumResult) {
        super(cells);
        expectedSumResult = sumResult;
    }

    public SumRule(Cell initialCell, Direction direction, Integer sumResult) {
        super(initialCell, direction);
        expectedSumResult = sumResult;
    }


    public boolean check() {
        Integer sum = 0;
        for (Cell cell : cells) {
            sum += cell.getData();
        }
        return sum.equals(expectedSumResult);
    }

    //TODO: if this implementation is chosen then replace check() with this method
    public boolean directionalCheck() {
        Integer sum = 0;
        //TODO: initialCell might or might NOT be an InputCell
        Cell currentCell = this.initialCell;
        while ( currentCell != null ) {
            sum += currentCell.getData();
            currentCell = this.direction.getNextCell(currentCell);
        }
        return sum.equals(this.expectedSumResult);
    }
}
