package ar.fiuba.tdd.tp1.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;

import java.util.Collection;

/*  */
public class AccumulatorRule extends Rule {
    private ArithmeticalOperator operator;
    private ComparisonOperator comparisonOperator;
    private int expectedValue;
    private int initAcumulator;
    //private ComparisonOperator atLeastOneEmptyCellComparisonOperator;

    public AccumulatorRule(Integer expectedValue, ArithmeticalRuleOperators operators, Integer initAcumulator) {
        this.expectedValue = expectedValue;
        this.operator = operators.getOperator();
        this.comparisonOperator = operators.getComparisonOperator();
        this.initAcumulator = initAcumulator;
        //this.atLeastOneEmptyCellComparisonOperator = operators.getAtLeastOneCellIsEmptyComparisonOperator();
    }

    public boolean check(Graph graph) {
        Collection<Cell> cells = graph.getCells();
        Integer accumulator = initAcumulator;
        for (Cell cell : cells) {
            if (cell.isEmpty()) {
                return false;
            }
            accumulator = operator.apply(accumulator, Integer.parseInt(cell.getData()));
        }
        return comparisonOperator.compare(accumulator, expectedValue);

        /*
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

        return comparisonOperator.compare(accumulator, accumulatorExpectedValue);*/
    }
}