/*
package ar.fiuba.tdd.tp1.model.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.factory.CellViewFactory;
import ar.fiuba.tdd.tp1.view.CellView;
import ar.fiuba.tdd.tp1.view.DataCellView;
import ar.fiuba.tdd.tp1.view.NullCellView;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CellViewFactoryTests {

    CellViewFactory cellViewFactory = new CellViewFactory();

    @Test
    public void creatingACellViewWithTheTypeDataMustReturnADataCellView() {
        CellView cell = cellViewFactory.create(new InputCell("1"), Cell.DATA_TYPE);

        assertTrue(cell instanceof DataCellView);
    }

    @Test
    public void creatingACellViewWithTheTypeNullCellMustReturnANullCellView() {
        CellView cell = cellViewFactory.create(new NullCell(), Cell.NULL_TYPE);

        assertTrue(cell instanceof NullCellView);
    }

    @Test
    public void creatingACellViewWithAnUndefinedTypeMustReturnNull() {
        CellView cell = cellViewFactory.create(new InputCell("2"), "alksjdflsjdfl");
        assertEquals(cell, null);
    }


}
*/
