package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.InputCell;

public class DataCellView implements CellView {

    private InputCell cell;

    public DataCellView(InputCell cell) {
        this.cell = cell;
    }

    @Override
    public void draw() {
        System.out.println("# " + this.cell.getData() + " #");
    }
}
