package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.BaseRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/**
 * Created by lucas on 27/09/16.
 */
public class RuleFactory {

    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals("no_rep")) {
            return new NoRepetitionRule(cellPositions, walkObject);
        } /*else if (type.equals("sum")) {
            return new SumRule(cellPositions);
        }*/
        return null;
    }
}
