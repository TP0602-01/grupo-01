package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.Collection;

/*  */
abstract class BaseRule implements IRule {

    protected Collection<Cell> cells;

    BaseRule(Collection<Cell> cells) {
        this.cells = cells;
    }

    public abstract boolean check();
}
