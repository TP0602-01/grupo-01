package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.AccumulatorRule;
import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;


public enum RuleCreator {


    NO_REPETITION_RULE_CREATOR("no_rep") {
        @Override
        public BaseRule createRule(Walk walkObject, Collection<String> cellPositions, ArithmeticalRuleOperators operators) {

            return new NoRepetitionRule(cellPositions,walkObject);
        }
    },

    ACCUMULATOR_RULE_CREATOR("accum") {
        @Override
        public BaseRule createRule(Walk walkObject, Collection<String> cellPositions, ArithmeticalRuleOperators operators) {

            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split(DELIMITER)[0]);
            int comparisonValue = Integer.parseInt(initCells.split(DELIMITER)[1]);
            return new AccumulatorRule(cellPositions, walkObject, comparisonValue,
                    operators);
        }

    };

    private static final String DELIMITER = "_";

    public final String stringRepresentation;

    RuleCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }


    public abstract BaseRule createRule(Walk walkObject, Collection<String> cellPositions, ArithmeticalRuleOperators operators);


}
