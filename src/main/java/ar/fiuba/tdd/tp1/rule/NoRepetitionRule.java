package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*  */
public class NoRepetitionRule extends BaseRule {

    public NoRepetitionRule(Collection<Cell> cells) {
        super(cells);
    }

    public boolean check() {
        Set<Integer> set = new HashSet<>();
        for (Cell cell : cells) {
            if (!set.add(cell.getData())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean directionalCheck() {
        //TODO: implement
        return false;
    }
}
