package ar.fiuba.tdd.tp1.rule.utilities;

public enum ArithmeticalOperator {

    MULT("*") {
        @Override
        public Integer apply(Integer x1, Integer x2) { return x1 * x2; }
    },

    ADDITION("+") {
        @Override
        public Integer apply(Integer x1, Integer x2) {
            return x1 + x2;
        }
    };

    private final String stringRepresentation;

    ArithmeticalOperator(String text) {
        this.stringRepresentation = text;
    }

    // Yes, enums *can* have abstract methods. This code compiles...
    public abstract Integer apply(Integer x1, Integer x2);


}
