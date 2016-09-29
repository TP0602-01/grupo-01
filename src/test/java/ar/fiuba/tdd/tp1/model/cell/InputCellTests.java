package ar.fiuba.tdd.tp1.model.cell;

import ar.fiuba.tdd.tp1.cell.InputCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InputCellTests {

    @Test
    public void creatingAnInputCellWithAnIntegerAndAskingForItMustReturnTheCorrectValue() {
        InputCell anInputCell = new InputCell("1");
        assertTrue(anInputCell.getData().equals("1"));
    }

    @Test
    public void usingTheMethodSetDataMustChangeTheDataContained() {
        InputCell anInputCell = new InputCell("3");
        anInputCell.setData("1");
        assertTrue(anInputCell.getData().equals("1"));
    }
}
