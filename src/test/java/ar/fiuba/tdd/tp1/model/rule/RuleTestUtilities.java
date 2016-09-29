package ar.fiuba.tdd.tp1.model.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Vector;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RuleTestUtilities {

    public Walk createAWalkMock(int initialRow, int initialColumn, String[][] cellData) {
        Walk walkMock = mock(Walk.class);
        Vector<Cell> collectionReturnedByWalk = new Vector<>();
        for (int row = 0; row < cellData.length; row++) {
            for (int column = 0; column < cellData[row].length; column++) {
                collectionReturnedByWalk.add(new InputCell(cellData[row][column]));
            }
        }
        when(walkMock.getCellList(initialRow, initialColumn)).thenReturn(collectionReturnedByWalk);
        return walkMock;
    }

}
