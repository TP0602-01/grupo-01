package ar.fiuba.tdd.tp1.cell;

import java.util.Set;

/*  */
public class FixedCell extends Cell {
    public FixedCell(String data) {
        super(data);
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
