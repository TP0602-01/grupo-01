package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;

/*  */
public abstract class Cell extends Observable {
    protected Integer data;

    Cell(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return this.data;
    }

}
