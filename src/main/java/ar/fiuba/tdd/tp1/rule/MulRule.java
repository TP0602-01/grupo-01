package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.Vector;

/*  */
public class MulRule extends BaseRule {

    private int expectedSumResult;

    public MulRule(Collection<String> cellsAsString, Walk walk, Integer sumResult) {
        super(cellsAsString, walk);
        this.expectedSumResult = sumResult;
    }

    public boolean check() {
        Integer mul = 1;

        for (String cellAsString: cellsAsString) {
            for (Cell cellValue: calculateCellList(cellAsString)) {
                mul *= Integer.parseInt( cellValue.getData() );
            }
        }
        return mul.equals(expectedSumResult);
    }

    protected Vector<Cell> calculateCellList(String cellAsString) {
        String firstPos = cellAsString.split("-")[0];
        String endPos = cellAsString.split("-")[1];
        return walk.getCellList(firstPos, endPos);
    }


}
