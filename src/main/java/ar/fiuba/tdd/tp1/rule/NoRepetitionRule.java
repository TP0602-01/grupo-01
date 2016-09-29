package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*  */
public class NoRepetitionRule extends BaseRule {

    public NoRepetitionRule(Collection<String> cells, Walk walk) {
        super(cells, walk);
    }

    public boolean check() {

        for (String cellAsString : cellsAsString) {
            Set<Integer> set = new HashSet<>();
            for (Cell cellValue: calculateCellList(cellAsString)) {
                if (!set.add(Integer.parseInt(cellValue.getData()))) {
                    return false;
                }
            }
        }
        return true;
    }
}

