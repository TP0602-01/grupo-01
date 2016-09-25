package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;

/*  */
public abstract class Cell extends Observable {
    protected Integer data;
    private Cell rightCell;

    Cell(Integer data) {
        this.data = data;
    }

    public Integer getData() {
        return this.data;
    }

    public Cell getRightCell() {
        return this.rightCell;
    }

    public void setRightCell(Cell cell) {
        this.rightCell = cell;
    }
}
