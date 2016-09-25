package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.direction.Direction;

import java.util.Collection;

/*  */
public abstract class BaseRule implements IRule {

    //TODO: if Directional implementation is chosen, then delete Collections
    protected Collection<Cell> cells;

    protected Cell initialCell;
    protected Direction direction;

    BaseRule(Collection<Cell> cells) {
        this.cells = cells;
    }

    BaseRule(Cell initialCell, Direction direction){
        this.initialCell = initialCell;
        this.direction = direction;
    }

    public abstract boolean check();
    public abstract boolean directionalCheck();
}
