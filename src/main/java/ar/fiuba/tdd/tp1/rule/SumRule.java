package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.Vector;

/*  */
public class SumRule extends BaseRule {


    private int expectedSumResult;

    public SumRule(Collection<String> cells, Walk walk, Integer sumResult) {
        super(cells, walk);
        this.expectedSumResult = sumResult;
    }

    public boolean check() {
        Integer sum = 0;
        for (String cell: cells) {
            Integer intX = Integer.parseInt(cell.split(",")[0]);
            Integer intY = Integer.parseInt(cell.split(",")[1]);

            Vector<Cell> cellList = walk.getCellList(intX, intY);

            for (Cell cellValue: cellList) {
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
