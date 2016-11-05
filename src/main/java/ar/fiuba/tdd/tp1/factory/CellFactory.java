package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.factory.creator.CellCreator;

import java.util.HashMap;


public class CellFactory {

    private static HashMap<String, CellCreator> cellCreators;

    private static void initializeCreators() {
        cellCreators = new HashMap<>();
        for (CellCreator creator : CellCreator.values()) {
            cellCreators.put(creator.stringRepresentation, creator);
        }
    }


    public static Cell create(String type, String content) {
        if (cellCreators == null) {
            initializeCreators();
        }
        CellCreator cellCreator;
        return (cellCreator = cellCreators.get(type)) == null ? new NullCell() : cellCreator.createCell(content);
    }
}
