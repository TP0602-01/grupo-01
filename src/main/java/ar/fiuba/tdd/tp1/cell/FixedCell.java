package ar.fiuba.tdd.tp1.cell;

/*
 * Fixed cell is a cell than cant change its data
 * and is used to show a rule in a set.
 *
 */
public class FixedCell extends Cell {
    public FixedCell(String data) {
        super(data);
    }

    public boolean isEmpty() {
        return false;
    }

}
