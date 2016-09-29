package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;

/*  */
public abstract class Cell extends Observable {
    protected Integer data;

    Cell(Integer data) {
        this.data = data;
    }

    public abstract boolean isEmpty();

    public Integer getData() {
        return this.data;
    }

    // TODO: borrarlo despues
    public void setData(Integer newValue) {
        System.out.println("trying to set value" + newValue);
    }

}
