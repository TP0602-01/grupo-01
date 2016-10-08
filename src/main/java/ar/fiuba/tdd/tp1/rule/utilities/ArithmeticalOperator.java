package ar.fiuba.tdd.tp1.rule.utilities;

public enum ArithmeticalOperator {


    ADDITION("+") {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
    };


    private final String stringRepresentation;

    ArithmeticalOperator(String text) {
        this.stringRepresentation = text;
    }

    // Yes, enums *can* have abstract methods. This code compiles...
    public abstract double apply(double x1, double x2);




}
