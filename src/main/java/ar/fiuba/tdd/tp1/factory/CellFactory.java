package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;

/* */
public class CellFactory {
    public static Cell create(String type, String content) {
        if (type.equals("data")) {
            return new InputCell("0");
        } else if (type.equals("nullcell")) {
            return new FixedCell(null);
        } else if (type.equals("kakoru")) {
            return new FixedCell(content);
        } else {
            return null;
        }
    }
}
