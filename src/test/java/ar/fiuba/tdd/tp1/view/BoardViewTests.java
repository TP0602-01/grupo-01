package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import org.junit.Test;

/**
 * Created by juanma on 28/09/16.
 */
public class BoardViewTests {

    @Test
    public void testInitiateABoardView() {
        int columns = 3;
        int rows = 2;
        GameBoard gameBoard = new GameBoard(columns,rows);
        BoardView boardView = new BoardView(gameBoard);

        InputCell a1, a2, a3, b1, b2, b3;
        a1 = new InputCell();
        a2 = new InputCell();
        a3 = new InputCell();
        b1 = new InputCell();
        b2 = new InputCell();
        b3 = new InputCell();

        b2.setData(5);

        CellView cellViewA1 = new DataCellView(a1);
        CellView cellViewA2 = new DataCellView(a2);
        CellView cellViewA3 = new DataCellView(a3);
        CellView cellViewB1 = new DataCellView(b1);
        CellView cellViewB2 = new DataCellView(b2);
        CellView cellViewB3 = new DataCellView(b3);

        boardView.addCellViewIn(cellViewA1, 0, 0);
        boardView.addCellViewIn(cellViewA2, 0, 1);
        boardView.addCellViewIn(cellViewA3, 0, 2);
        boardView.addCellViewIn(cellViewB1, 1, 0);
        boardView.addCellViewIn(cellViewB2, 1, 1);
        boardView.addCellViewIn(cellViewB3, 1, 2);

        boardView.update();
    }

    @Test
    public void testKakoruCellsView() {
        int columns = 3;
        int rows = 2;
        GameBoard gameBoard = new GameBoard(columns,rows);
        BoardView boardView = new BoardView(gameBoard);

        CellView cellViewA1 = new KakoruCellView(5,10);
        CellView cellViewA2 = new KakoruCellView(10,5);
        CellView cellViewA3 = new KakoruCellView(null,null);
        CellView cellViewB1 = new KakoruCellView(null,10);
        CellView cellViewB2 = new KakoruCellView(5,null);

        InputCell b3 = new InputCell();
        CellView cellViewB3 = new DataCellView(b3);

        boardView.addCellViewIn(cellViewA1, 0, 0);
        boardView.addCellViewIn(cellViewA2, 0, 1);
        boardView.addCellViewIn(cellViewA3, 0, 2);
        boardView.addCellViewIn(cellViewB1, 1, 0);
        boardView.addCellViewIn(cellViewB2, 1, 1);
        boardView.addCellViewIn(cellViewB3, 1, 2);

        boardView.update();
    }
}
