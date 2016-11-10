package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.factory.creator.RuleCreator;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.*;

import java.util.HashMap;

/*
 * Rule Factory that uses a Rule Creator to
 * different rule types.
 *
 */
public class RuleFactory {

    private static HashMap<String, RuleCreator> ruleCreators = null;

    public static Rule create(String type, String value) {
        if (ruleCreators == null) {
            initializeCreators();
        }

        RuleCreator creator;
        if (isAValidType(type)) {
            creator = ruleCreators.get(type);
            return creator.createRule(value);
        }
        return null;

    }


    private static boolean isAValidType(String type) {
        return (ruleCreators.get(type) != null);
    }

    private static void initializeCreators() {
        ruleCreators = new HashMap<>();

        for (RuleCreator creator : RuleCreator.values()) {
            ruleCreators.put(creator.stringRepresentation, creator);
        }
    }


}
