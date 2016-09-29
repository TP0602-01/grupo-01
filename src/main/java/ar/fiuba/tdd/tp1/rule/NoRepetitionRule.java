package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/*  */
public class NoRepetitionRule extends BaseRule {

    public NoRepetitionRule(Collection<String> cells, Walk walk) {
        super(cells, walk);
    }

    public boolean check() {
        Set<Integer> set = new HashSet<>();

        for (String cell : cells) {
            Integer intX = Integer.parseInt(cell.split(",")[0]);
            Integer intY = Integer.parseInt(cell.split(",")[1]);

            Vector<Cell> cellList = walk.getCellList(intX, intY);

            for (Cell cellValue : cellList) {
                if (!set.add(Integer.parseInt(cellValue.getData()))) {
                    return false;
                }
            }
        }
        return true;
    }
}

