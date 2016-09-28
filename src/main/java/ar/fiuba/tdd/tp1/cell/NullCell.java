package ar.fiuba.tdd.tp1.cell;

/**
 */
public class NullCell extends Cell {
    NullCell() {
        super(null);
    }

    public boolean isEmpty(){
        return true;
    }
}
