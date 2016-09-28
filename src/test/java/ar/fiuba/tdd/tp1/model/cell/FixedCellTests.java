package ar.fiuba.tdd.tp1.model.cell;

import ar.fiuba.tdd.tp1.cell.FixedCell;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FixedCellTests {

    @Test
    public void creatingAFixedCellAndAskingForItsValue(){
        FixedCell fixedCell = new FixedCell(1);
        assertEquals((int)fixedCell.getData(),1);
    }
}
