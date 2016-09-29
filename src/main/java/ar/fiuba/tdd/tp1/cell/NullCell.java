package ar.fiuba.tdd.tp1.cell;

/* */
public class NullCell extends Cell {
    public NullCell() {
        super(null);
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }
}
