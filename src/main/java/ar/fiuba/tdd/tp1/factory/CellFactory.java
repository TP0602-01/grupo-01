package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;

import java.util.HashMap;


public class CellFactory {

    private static HashMap<String, CellCreator> cellCreators = null;

    private static void initializeCreators() {

        cellCreators = new HashMap<>();

        CellCreator dataCellCreator = InputCell::new;
        cellCreators.put(Cell.DATA_TYPE, dataCellCreator);

        CellCreator nullCellCreator = (param) -> new NullCell();
        cellCreators.put(Cell.NULL_TYPE, nullCellCreator);

        CellCreator hintCellCreator = FixedCell::new;
        cellCreators.put(Cell.HINT_TYPE, hintCellCreator);

        CellCreator kakoruCellCreator = FixedCell::new;
        cellCreators.put(Cell.KAKORU_TYPE, kakoruCellCreator);
    }


    public static Cell create(String type, String content) {
        if (cellCreators == null) {
            initializeCreators();
        }
        CellCreator cellCreator = cellCreators.get(type);
        return (cellCreator = cellCreators.get(type)) == null ? null : cellCreator.createCell(content);
    }
}
