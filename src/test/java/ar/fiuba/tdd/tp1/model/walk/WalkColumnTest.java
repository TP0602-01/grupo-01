package ar.fiuba.tdd.tp1.model.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.walk.Walk;
import ar.fiuba.tdd.tp1.walk.WalkColumn;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class WalkColumnTest {
    GameBoard gameBoardMock;
    Collection<Cell> cells;
    ArrayList<Integer> dataStored = new ArrayList<>();
    private int initialRow = 0;
    private int initialColumn = 0;
    private int columnSize = 2;

    @Before
    public void setUp() {
        gameBoardMock = mock(GameBoard.class);
        when(gameBoardMock.getHeigth()).thenReturn(2);
        for (int row = initialRow; row < columnSize; row++) {
            dataStored.add(row);
            when(gameBoardMock.getCell(row, initialColumn)).thenReturn(new InputCell(Integer.toString(row)));
        }
        when(gameBoardMock.getCell(initialRow + columnSize, initialColumn)).thenReturn(new NullCell());
        Walk walk = new WalkColumn(gameBoardMock);
        cells = walk.getCellList(Integer.toString(initialRow) + "," + Integer.toString(initialColumn));
    }

    @Test
    public void theAmountOfCellsReturnedByTheGetCellListMethodIsCorrect() {
        assertEquals(columnSize, cells.size());
    }

    @Test
    public void theCellsReturnedHaveTheCorrectValues() {
        boolean theDataIsContained;
        for (Integer data : dataStored) {
            theDataIsContained = false;
            for (Cell cell : cells) {
                if (data.toString().equals(cell.getData())) {
                    theDataIsContained = true;
                }
            }
            assertTrue(theDataIsContained);
        }

    }
}
