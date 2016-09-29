package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;

public class DataCellView implements CellView {

    private Cell cell;

    public DataCellView(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String ASCIIdraw() {
        if (this.cell.getData() == null) {
            return "|     |";
        } else {
            return ("|  " + this.cell.getData() + "  |");
        }
    }


}
