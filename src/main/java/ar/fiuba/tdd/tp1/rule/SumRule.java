package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.Collection;

/*  */
class SumRule extends BaseRule {

    private Integer expectedSumResult;

    SumRule(Collection<Cell> cells, Integer sumResult) {
        super(cells);
        expectedSumResult = sumResult;
    }

    public boolean check() {
        Integer sum = 0;
        for (Cell cell : cells) {
            sum += cell.getData();
        }
        return sum.equals(expectedSumResult);
    }
}
