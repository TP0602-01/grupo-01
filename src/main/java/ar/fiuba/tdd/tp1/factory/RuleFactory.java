package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.HashMap;

/* */
public class RuleFactory {

    private static HashMap<String, RuleCreator> ruleCreators = null;


    private static void initializeCreators() {
        ruleCreators = new HashMap<>();

        for (RuleCreator creator: RuleCreator.values()) {
            ruleCreators.put(creator.stringRepresentation, creator);
        }

    }


    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions, ArithmeticalRuleOperators operators) {
        if (ruleCreators == null) {
            initializeCreators();
        }

        RuleCreator creator ;
        return ( creator = ruleCreators.get(type) ) == null ? null : creator.createRule(walkObject,cellPositions,operators);

    }
}
