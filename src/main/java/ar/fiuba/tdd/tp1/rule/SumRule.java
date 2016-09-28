package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/*  */
class SumRule extends BaseRule {

    SumRule(Collection<String> cells, Walk walk, Integer sumResult) {
        super(cells, walk);
    }

    public boolean check() {
        /*Integer sum = 0;
        for (Cell cell : cells) {
            sum += cell.getData();
        }
        return sum.equals(expectedSumResult);*/
        return true;    //TODO: CAMBIAR
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
