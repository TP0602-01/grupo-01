package ar.fiuba.tdd.tp1.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;



public class AccumulatorRule extends BaseRule {

    private ArithmeticalOperator arithmeticalOperator;
    private ComparisonOperator atLeastOneEmptyCellComparisonOperator;
    private ComparisonOperator comparisonOperator;
    private int accumulatorExpectedValue;

    public AccumulatorRule(Collection<String> cellsAsString, Walk walk,
                           Integer accumulatorExpectedValue,
                           ArithmeticalOperator arithmeticalOperator,
                           ComparisonOperator atLeastOneEmptyCellComparisonOperator,
                           ComparisonOperator comparisonOperator ) {

        super(cellsAsString, walk);
        this.accumulatorExpectedValue = accumulatorExpectedValue;
        this.arithmeticalOperator = arithmeticalOperator;
        this.atLeastOneEmptyCellComparisonOperator = atLeastOneEmptyCellComparisonOperator;
        this.comparisonOperator = comparisonOperator;

    }

    public boolean check() {
        double accumulator = 0;
        double auxValue;
        boolean atLeasOneCellIsEmpty = false;

        for (String cellAsString: cellsAsString) {
            for (Cell cellValue: calculateCellList(cellAsString)) {
                if (cellValue.isEmpty()) {
                    atLeasOneCellIsEmpty = true;
                }
                auxValue = Integer.parseInt( cellValue.getData() );
                accumulator = arithmeticalOperator.apply(accumulator,auxValue);
            }
        }

        if(atLeasOneCellIsEmpty) {
            return atLeastOneEmptyCellComparisonOperator.compare(accumulator,accumulatorExpectedValue);
        }

        return comparisonOperator.compare(accumulator,accumulatorExpectedValue);


    }
}
