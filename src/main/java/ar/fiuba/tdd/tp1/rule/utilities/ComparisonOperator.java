package ar.fiuba.tdd.tp1.rule.utilities;

/*
 * Comparison Operations for Rules. It is used to check
 * cell two contents
 *
 */
public enum ComparisonOperator {

    LESSOREQUAL("<=") {
        @Override
        public boolean compare(double leftValue, double rightValue) {
            return (leftValue <= rightValue);
        }

    },

    LESS("<") {
        @Override
        public boolean compare(double leftValue, double rightValue) {
            return (leftValue < rightValue);
        }
    },

    EQUAL("=") {
        @Override
        public boolean compare(double leftValue, double rightValue) {
            return (leftValue == rightValue);
        }
    };


    String stringRepresentation;

    ComparisonOperator(String operator) {
        this.stringRepresentation = operator;
    }

    public abstract boolean compare(double x1, double x2);

}
