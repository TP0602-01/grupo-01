package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.factory.creator.CellViewComponentCreator;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Create Cell View Component Factory using a Cell View Creator to
 * different cell view types.
 *
 */
public class CellViewComponentFactory {
    private static HashMap<String, CellViewComponentCreator> cellViewComponentCreators;

    public static CellViewComponent create(String type, ArrayList<String> content) {
        if (cellViewComponentCreators == null) {
            initializeCreators();
        }
        CellViewComponentCreator cellCreator;
        return (cellCreator = cellViewComponentCreators.get(type)) == null ? null : cellCreator.createCellViewComponent(content);
    }

    private static void initializeCreators() {
        cellViewComponentCreators = new HashMap<>();
        for (CellViewComponentCreator creator : CellViewComponentCreator.values()) {
            cellViewComponentCreators.put(creator.stringRepresentation, creator);
        }
    }

}
