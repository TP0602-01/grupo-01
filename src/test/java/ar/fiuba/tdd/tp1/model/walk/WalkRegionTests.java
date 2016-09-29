package ar.fiuba.tdd.tp1.model.walk;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.walk.Walk;
import ar.fiuba.tdd.tp1.walk.WalkColumn;
import ar.fiuba.tdd.tp1.walk.WalkRegion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WalkRegionTests {
    GameBoard gameBoardMock;
    private int initialRow = 0;
    private int initialColumn = 0;
    private int regionSize = 2;
    Collection<Cell> cells;
    ArrayList<Integer> dataStored = new ArrayList<>();

    @Before
    public void setUp() {
        gameBoardMock = mock(GameBoard.class);
        when(gameBoardMock.getHeigth()).thenReturn(2);
        when(gameBoardMock.getWidth()).thenReturn(2);
        int data;
        for (int col = initialColumn; col < regionSize; col++) {
            for (int row = initialRow; row < regionSize; row++) {
                data = col + 100 * row;
                dataStored.add(data);
                when(gameBoardMock.getCell(row, col)).thenReturn(new InputCell(Integer.toString(data)));
            }
        }
        WalkRegion walk = new WalkRegion(gameBoardMock);
        walk.setSizeRegion(regionSize);
        cells = walk.getCellList(initialRow, initialColumn);
    }


    @Test
    public void theSizeOfTheCellCollectionObtainedByTheGetCellListMustBeCorrect() {
        assertEquals(regionSize * 2, cells.size());
    }


    @Test
    public void theCellsReturnedHaveTheCorrectValues() {
        boolean theDataIsContained;
        for (Integer data : dataStored) {
            theDataIsContained = false;
            for (Cell cell : cells) {
                if (data.equals(cell.getData())) {
                    theDataIsContained = true;
                }
            }
            assertTrue(theDataIsContained);
        }

    }
}
