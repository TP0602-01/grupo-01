package ar.fiuba.tdd.tp1.cell;

/*  */
public class FixedCell extends Cell {
    public FixedCell(String data) {
        super(data);
    }

    public boolean isEmpty() {
        return false;
    }
}
