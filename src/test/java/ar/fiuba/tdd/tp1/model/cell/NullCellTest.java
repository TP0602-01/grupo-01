package ar.fiuba.tdd.tp1.model.cell;

import ar.fiuba.tdd.tp1.cell.NullCell;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;


public class NullCellTest {
    @Test
    public void nullCellIsEmpty() {
        NullCell cell = new NullCell();
        assertFalse(cell.isEmpty());
    }

    @Test
    public void nullCellIsNotWalkeable() {
        NullCell cell = new NullCell();
        assertFalse(cell.isWalkable());
    }
}
