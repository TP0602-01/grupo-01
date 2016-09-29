package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;

/*  */
public abstract class Cell extends Observable {
    protected String data;

    Cell(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

}
