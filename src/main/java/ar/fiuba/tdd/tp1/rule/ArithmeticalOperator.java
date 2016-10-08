package ar.fiuba.tdd.tp1.rule;

public enum ArithmeticalOperator {


    ADDITION("+") {
        @Override
        public double apply(double x1, double x2) {
            return x1 + x2;
        }
    },
    SUBTRACTION("-") {
        @Override
        public double apply(double x1, double x2) {
            return x1 - x2;
        }
    };


    private final String text;

    ArithmeticalOperator(String text) {
        this.text = text;
    }

    // Yes, enums *can* have abstract methods. This code compiles...
    public abstract double apply(double x1, double x2);

}
