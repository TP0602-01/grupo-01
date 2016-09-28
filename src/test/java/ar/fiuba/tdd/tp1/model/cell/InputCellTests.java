package ar.fiuba.tdd.tp1.model.cell;


import ar.fiuba.tdd.tp1.cell.InputCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputCellTests {



    @Test
    public void creatingAnInputCellWithAnIntegerAndAskingForItMustReturnTheCorrectValue() {
        InputCell anInputCell = new InputCell(1);
        assertEquals((int)anInputCell.getData(),1);
    }

    @Test
    public void usingTheMethodSetDataMustChangeTheDataContained() {
        InputCell anInputCell = new InputCell(3);
        anInputCell.setData(1);
        assertEquals((int)anInputCell.getData(),1);
    }

}
