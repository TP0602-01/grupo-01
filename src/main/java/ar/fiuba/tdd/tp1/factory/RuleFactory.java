package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/* */
public class RuleFactory {

    public static final String NO_REPETITION_TYPE = "no_rep";
    public static final String SUM_TYPE = "sum";
    public static final String MUL_TYPE = "mul";
    public static final String DELIMITER = "_";

    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals(NO_REPETITION_TYPE)) {
            return new NoRepetitionRule(cellPositions, walkObject);
        } else {
            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split(DELIMITER)[0]);
            int sumNumber = Integer.parseInt(initCells.split(DELIMITER)[1]);

            if (type.equals(SUM_TYPE)) {
                return new SumRule(cellPositions, walkObject, sumNumber);
            } else if (type.equals(MUL_TYPE)) {
                return new MulRule(cellPositions, walkObject, sumNumber);
            }
        }
        return null;
    }
}
