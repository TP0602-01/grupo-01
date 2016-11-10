package ar.fiuba.tdd.tp1.rule.utilities;

/*
 * Arithmetical Rule Operations have an Arithmetical operation and
 * a comparition operation. It is used by Rules.
 *
 */
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
