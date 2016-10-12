package ar.fiuba.tdd.tp1.rule.utilities;

import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;


public class ArithmeticalRuleOperators {

    private ArithmeticalOperator operator;
    private ComparisonOperator comparisonOperator;
    private ComparisonOperator atLeastOneCellIsEmptyComparisonOperator;

    public ArithmeticalRuleOperators(ArithmeticalOperator operator, ComparisonOperator comparisonOperator,
                                     ComparisonOperator atLeastOneCellIsEmptyComparisonOperator) {
        this.operator = operator;
        this.atLeastOneCellIsEmptyComparisonOperator = atLeastOneCellIsEmptyComparisonOperator;
        this.comparisonOperator = comparisonOperator;
    }

    public ArithmeticalOperator getOperator() {
        return this.operator;
    }

    public ComparisonOperator getComparisonOperator() {
        return this.comparisonOperator;
    }

    public ComparisonOperator getAtLeastOneCellIsEmptyComparisonOperator() {
        return this.atLeastOneCellIsEmptyComparisonOperator;
    }

}
