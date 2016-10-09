package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/* */
public class RuleFactory {

    public static final String NO_REPETITION_TYPE = "no_rep";
    public static final String SUM_TYPE = "sum";
    private static final String DELIMITER = "_";

    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals(NO_REPETITION_TYPE)) {
            return new NoRepetitionRule(cellPositions, walkObject);
        } else if (type.equals(SUM_TYPE)) {
            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split(DELIMITER)[0]);
            int sumNumber = Integer.parseInt(initCells.split(DELIMITER)[1]);

            return new SumRule(cellPositions, walkObject, sumNumber);
        }
        return null;
    }
}
