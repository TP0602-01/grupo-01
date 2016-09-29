package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;

/**
 * Created by lucas on 27/09/16.
 */
public class CellFactory {
    public static Cell create(String type) {
        if (type.equals("data")) {
            return new InputCell("0");
        }
        if (type.equals("nullcell")) {
            return new FixedCell(null);
        }
        if (type.equals("kakoru")) {
            return new FixedCell(null);
        }
        return null;
    }
}
