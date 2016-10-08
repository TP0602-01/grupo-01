package ar.fiuba.tdd.tp1.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;


public class AccumulatorRule extends BaseRule {

    private ArithmeticalOperator arithmeticalOperator;
    private ComparisonOperator atLeastOneEmptyCellComparisonOperator;
    private ComparisonOperator comparisonOperator;
    private int accumulatorExpectedValue;

    public AccumulatorRule(Collection<String> cellsAsString, Walk walk,
                           Integer accumulatorExpectedValue,
                           ArithmeticalRuleOperators operators) {

        super(cellsAsString, walk);
        this.accumulatorExpectedValue = accumulatorExpectedValue;
        this.arithmeticalOperator = operators.getOperator();
        this.atLeastOneEmptyCellComparisonOperator = operators.getAtLeastOneCellIsEmptyComparisonOperator();
        this.comparisonOperator = operators.getComparisonOperator();

    }

    public boolean check() {
        Integer accumulator = 0;
        Integer cellValueAsInteger;
        boolean atLeasOneCellIsEmpty = false;

        for (String cellAsString : cellsAsString) {
            for (Cell cellValue : calculateCellList(cellAsString)) {
                atLeasOneCellIsEmpty = atLeasOneCellIsEmpty | cellValue.isEmpty();
                cellValueAsInteger = Integer.parseInt(cellValue.getData());
                accumulator = arithmeticalOperator.apply(accumulator, cellValueAsInteger);
            }
        }

        if (atLeasOneCellIsEmpty) {
            return atLeastOneEmptyCellComparisonOperator.compare(accumulator, accumulatorExpectedValue);
        }

        return comparisonOperator.compare(accumulator, accumulatorExpectedValue);


    }

}
