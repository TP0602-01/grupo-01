package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.HashMap;

/* */
public class RuleFactory {

    public static final String NO_REPETITION_TYPE = "no_rep";
    public static final String SUM_TYPE = "sum";
    public static final String ACCUMULATOR = "accum";
    public static final String DELIMITER = "_";

    private static HashMap<String,RuleCreator> ruleCreators = null;


    private static void initializeCreators(){
        ruleCreators = new HashMap<>();

    }


    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals(NO_REPETITION_TYPE)) {
            return new NoRepetitionRule(cellPositions, walkObject);
        } else if (type.equals(SUM_TYPE)) {
            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split(DELIMITER)[0]);
            int sumNumber = Integer.parseInt(initCells.split(DELIMITER)[1]);


            return new AccumulatorRule(cellPositions, walkObject, sumNumber, ArithmeticalOperator.ADDITION,ComparisonOperator.EQUAL,ComparisonOperator.LESSOREQUAL);
        }
        return null;
    }
}
