package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.rule.MultRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.rule.SumRule;

public enum RuleCreator {


    NO_REPETITION_RULE_CREATOR("no_rep") {
        @Override
        public Rule createRule(String value) {
            return new NoRepetitionRule(value);
        }
    },

    SUM_RULE_CREATOR("sum") {   //TODO: LE PUSE UN SUMRULE, PERO DESPUES HACEMOS LO TUYO DIEGO

        @Override               //TODO: Y QUIZAS DA PARA PONER UN TEMPLATE METHOD
        public Rule createRule(String value) {
            return new SumRule(Integer.parseInt(value));
        }

    },

    MULT_RULE_CREATOR("mult") {   //TODO: LE PUSE UN MULTRULE, PERO DESPUES HACEMOS LO TUYO DIEGO

        @Override
        public Rule createRule(String value) {
            return new MultRule(Integer.parseInt(value));
        }

    };

    /*,SUM_RULE_CREATOR("sum") {
        @Override
        public Rule createRule(String value) {

            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split(DELIMITER)[0]);
            int comparisonValue = Integer.parseInt(initCells.split(DELIMITER)[1]);

            ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(
                    ArithmeticalOperator.ADDITION, ComparisonOperator.EQUAL,
                    ComparisonOperator.LESS
            );

            return new AccumulatorRule(cellPositions, walkObject, comparisonValue,
                    operators);
        }

    };*/

    private static final String DELIMITER = "_";

    public final String stringRepresentation;

    RuleCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }


    public abstract Rule createRule(String value);


}
