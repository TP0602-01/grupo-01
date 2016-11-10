package ar.fiuba.tdd.tp1.model.factory;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.factory.CellFactory;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CellFactoryTests {
    CellFactory cellFactory = new CellFactory();

    @Test
    public void creatingACellOfTypeDataMustReturnAnInputCell() {
        Cell cell = cellFactory.create(Cell.DATA_TYPE, "1");
        assertTrue(cell instanceof InputCell);
    }

    @Test
    public void creatingACellOfTypeNullMustReturnANullCell() {
        Cell cell = cellFactory.create(Cell.NULL_TYPE, "");
        assertTrue(cell instanceof NullCell);
    }


    @Test
    public void creatingACellOfAnUnknownTypeMustReturnNull() {
        Cell cell = cellFactory.create("ssssdf", "");
        assertEquals(cell.getClass(), NullCell.class);
    }


}
