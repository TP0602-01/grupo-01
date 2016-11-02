package ar.fiuba.tdd.tp1.model.cell;

import ar.fiuba.tdd.tp1.cell.FixedCell;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class FixedCellTest {
    @Test
    public void creatingAnFixedCellWithAnIntegerAndAskingForItMustReturnTheCorrectValue() {
        FixedCell anFixedCell = new FixedCell("1");
        assertTrue(anFixedCell.getData().equals("1"));
    }

    @Test
    public void creatingAnFixedCellWithAnIntegerAndTryingSetValue() {
        FixedCell anFixedCell = new FixedCell("1");
        anFixedCell.setData("3");
        assertTrue(anFixedCell.getData().equals("1"));
    }

    @Test
    public void creatingAnFixedCellWithAnIntegerAndItsWalkable() {
        FixedCell anFixedCell = new FixedCell("1");
        assertTrue(!anFixedCell.isWalkable());
    }

    @Test
    public void fixedCellIsNotWalkeable() {
        FixedCell inputCell = new FixedCell("");
        assertFalse(inputCell.isWalkable());
    }

    @Test
    public void fixedCellIsEmptyIsAlwaysFalse() {
        FixedCell inputCell = new FixedCell("");
        assertFalse(inputCell.isEmpty());
    }

}
