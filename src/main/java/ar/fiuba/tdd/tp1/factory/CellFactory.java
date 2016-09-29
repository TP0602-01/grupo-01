package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;

/* */
public class CellFactory {

    public static Cell create(String type, String content) {
        if (type.equals(Cell.DATA_TYPE)) {
            return new InputCell("0");
        } else if (type.equals(Cell.NULL_TYPE)) {
            return new FixedCell(null);
        } else if (type.equals(Cell.KAKORU_TYPE)) {
            return new FixedCell(content);
        } else {
            return null;
        }
    }
}
