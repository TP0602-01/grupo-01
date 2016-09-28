package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.direction.Direction;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;

/*  */
public abstract class BaseRule implements IRule {

    protected Collection<String> cells;
    protected Walk walk;

    BaseRule(Collection<String> cells, Walk walk) {
        this.cells = cells;
        this.walk = walk;
    }

    public abstract boolean check();
}
