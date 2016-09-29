package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/**
 * Created by lucas on 27/09/16.
 */
public class RuleFactory {

    public static BaseRule create(String type, Walk walkObject, Collection<String> cellPositions) {
        if (type.equals("no_rep")) {
            return new NoRepetitionRule(cellPositions, walkObject);
        }
        else if (type.equals("sum")) {
            String init_cells = (cellPositions.iterator()).next();

            cellPositions.clear();
            cellPositions.add(init_cells.split("_")[0]);
            int sumNumber = Integer.parseInt(init_cells.split("_")[1]);

            return new SumRule(cellPositions, walkObject, sumNumber);
        }
        return null;
    }
}
