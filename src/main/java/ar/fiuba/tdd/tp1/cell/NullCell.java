package ar.fiuba.tdd.tp1.cell;

/*
 * Null Cell is a cell than cant change its content
 * and represent a null state.
 *
 */
public class NullCell extends Cell {
    public NullCell() {
        super(null);
    }

    public boolean isEmpty() {
        return false;
    }

}
