package ar.fiuba.tdd.tp1.rule;


public enum ComparisonOperator {

    LESSOREQUAL("<") {

        @Override
        public boolean compare(double leftValue, double rightValue) {
            return ( leftValue <= rightValue ) ;
        }

    },

    EQUAL("=") {
        @Override
        public boolean compare(double leftValue, double rightValue) {
            return ( leftValue == rightValue ) ;
        }
    };



    String operator;

    ComparisonOperator(String operator) {
        this.operator = operator;
    }

    public abstract boolean compare(double x1, double x2);

}
