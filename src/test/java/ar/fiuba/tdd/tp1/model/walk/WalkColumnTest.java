package ar.fiuba.tdd.tp1.model.walk;
import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.walk.Walk;
import ar.fiuba.tdd.tp1.walk.WalkColumn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;


public class WalkColumnTest {
    GameBoard gameBoardMock;
    private int initialRow= 1;
    private int initialColumn = 1;
    private int columnSize = 2;
    Collection<Cell> cells;

    @Before
    public void setUp(){
        gameBoardMock = mock(GameBoard.class);
        when(gameBoardMock.getHeigth()).thenReturn(2);
        for(int col = initialColumn; col <= columnSize; col++){

            when(gameBoardMock.getCell(initialRow,col)).thenReturn(new InputCell(col));
            when(gameBoardMock.getCell(initialRow,col)).thenReturn(new InputCell(col));
        }
        Walk walk = new WalkColumn(gameBoardMock);
        cells = walk.getCellList(initialRow,initialColumn);
    }

    @Test
    public void theAmountOfCellsReturnedByTheGetCellListMethodIsCorrect(){
        assertEquals(columnSize,cells.size());
    }

    @Test
    public void theCellsReturnedHaveTheCorrectValues(){
        int data = initialColumn;
        for( Cell cell: cells) {
            assertEquals(data, (int)cell.getData());
            data++;
        }
    }
}
