package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.rule.AccumulatorRule;
import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;


public enum RuleCreator {


    NO_REPETITION_RULE_CREATOR("no_rep") {
        @Override
        public BaseRule createRule(Walk walkObject, Collection<String> cellPositions) {

            return new NoRepetitionRule(cellPositions, walkObject);
        }
    },

    SUM_RULE_CREATOR("sum") {
        @Override
        public BaseRule createRule(Walk walkObject, Collection<String> cellPositions) {

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

    };

    private static final String DELIMITER = "_";

    public final String stringRepresentation;

    RuleCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }


    public abstract BaseRule createRule(Walk walkObject, Collection<String> cellPositions);


}
