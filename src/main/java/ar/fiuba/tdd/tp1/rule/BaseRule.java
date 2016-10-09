package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;
import java.util.Vector;

/*  */
public abstract class BaseRule implements IRule {

    Collection<String> cellsAsString;
    protected Walk walk;

    BaseRule(Collection<String> cellsAsString, Walk walk) {
        this.cellsAsString = cellsAsString;
        this.walk = walk;
    }

    public abstract boolean check();

    Vector<Cell> calculateCellList(String cellAsString) {
        Integer intX = Integer.parseInt(cellAsString.split(",")[0]);
        Integer intY = Integer.parseInt(cellAsString.split(",")[1]);
        return walk.getCellList(intX, intY);
    }
}
