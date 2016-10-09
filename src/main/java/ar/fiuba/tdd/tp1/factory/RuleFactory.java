package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.factory.creator.RuleCreator;
import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.HashMap;

/* */
public class RuleFactory {


    private static HashMap<String, RuleCreator> ruleCreators = null;

    private static void initializeCreators() {
        ruleCreators = new HashMap<>();

        for (RuleCreator creator : RuleCreator.values()) {
            ruleCreators.put(creator.stringRepresentation, creator);
        }
    }


    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (ruleCreators == null) {
            initializeCreators();
        }

        RuleCreator creator;
        return (creator = ruleCreators.get(type)) == null ? null : creator.createRule(walkObject, cellPositions);

    }
}
