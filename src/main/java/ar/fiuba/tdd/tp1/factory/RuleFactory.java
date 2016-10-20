package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.factory.creator.RuleCreator;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.*;

import java.util.HashMap;

/* */
public class RuleFactory {

    private static HashMap<String, RuleCreator> ruleCreators = null;

    public static Rule create(String type, String value) {
        return create(type, value, null);
    }

    public static Rule create(String type, String value, GameBoard board) {
        if (ruleCreators == null) {
            initializeCreators();
        }

        RuleCreator creator;
        return (creator = ruleCreators.get(type)) == null ? null : creator.createRule(value, board);

    }

    private static void initializeCreators() {
        ruleCreators = new HashMap<>();

        for (RuleCreator creator : RuleCreator.values()) {
            ruleCreators.put(creator.stringRepresentation, creator);
        }
    }


}
