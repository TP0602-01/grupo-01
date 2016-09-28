package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/*  */
public class CorrectValueRule extends BaseRule {
    public CorrectValueRule(Collection<String> cells, Walk walk) {
        super(cells, walk);
    }

    @Override
    public boolean check() {
        return true;
    }
}
