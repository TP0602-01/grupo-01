package ar.fiuba.tdd.tp1.factory;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.creator.CellViewComponentCreator;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import java.util.Collection;
import java.util.HashMap;

public class CellViewComponentFactory {
    private static HashMap<String, CellViewComponentCreator> cellViewComponentCreators;

    private static void initializeCreators() {
        cellViewComponentCreators = new HashMap<>();
        for (CellViewComponentCreator creator : CellViewComponentCreator.values()) {
            cellViewComponentCreators.put(creator.stringRepresentation, creator);
        }
    }


    public static CellViewComponent create(String type, Collection<String> content) {
        if (cellViewComponentCreators == null) {
            initializeCreators();
        }
        CellViewComponentCreator cellCreator;
        return (cellCreator = cellViewComponentCreators.get(type)) == null ? null : cellCreator.createCellViewComponent(content);
    }

}
