package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;

public class DataCellView implements CellView {
    private Cell cell;

    public DataCellView(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String asciiDraw() {
        if ((this.cell.getData() == null) || (this.cell.getData() == "0")) {
            return "|     |";
        } else {
            return ("|  " + this.cell.getData() + "  |");
        }
    }
}
