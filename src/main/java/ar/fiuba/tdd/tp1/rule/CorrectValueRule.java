package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.Collection;

/*  */
public class CorrectValueRule extends BaseRule {
    public CorrectValueRule(Collection<Cell> cells) {
        super(cells);
    }

    @Override
    public boolean check() {
        return true;
    }

    @Override
    public boolean directionalCheck() {
        return true;
    }
}
