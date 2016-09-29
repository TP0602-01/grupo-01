package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/* */
public class RuleFactory {

    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals("no_rep")) {
            return new NoRepetitionRule(cellPositions, walkObject);
        } else if (type.equals("sum")) {
            String initCells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(initCells.split("_")[0]);
            int sumNumber = Integer.parseInt(initCells.split("_")[1]);

            return new SumRule(cellPositions, walkObject, sumNumber);
        }
        return null;
    }
}
