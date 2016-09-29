package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/*  */
public class SumRule extends BaseRule {

    private int expectedSumResult;

    public SumRule(Collection<String> cellsAsString, Walk walk, Integer sumResult) {
        super(cellsAsString, walk);
        this.expectedSumResult = sumResult;
    }

    public boolean check() {
        Integer sum = 0;

        for (String cellAsString: cellsAsString) {
            for (Cell cellValue: calculateCellList(cellAsString)) {
                sum += Integer.parseInt( cellValue.getData() );
            }
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
