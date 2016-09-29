package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import org.junit.Test;

/* */
public class BoardViewTests {

    @Test
    public void testSimulationInitiateABoardView() {
        int columns = 2;
        int rows = 2;
        //create a gameboard
        GameBoard gameBoard = new GameBoard(columns, rows);
        BoardView boardView = new BoardView(gameBoard);
        //asasasa
        InputCell a1 = new InputCell();
        InputCell a2 = new InputCell();
        InputCell b1 = new InputCell();
        InputCell b2 = new InputCell();
        //sasasasa
        b2.setData("5");

        CellView cellViewA1 = new DataCellView(a1);
        CellView cellViewA2 = new DataCellView(a2);
        CellView cellViewB1 = new DataCellView(b1);
        CellView cellViewB2 = new DataCellView(b2);
        //asasasasasasasas
        boardView.addCellViewIn(cellViewA1, 0, 0);
        boardView.addCellViewIn(cellViewA2, 0, 1);
        boardView.addCellViewIn(cellViewB1, 1, 0);
        boardView.addCellViewIn(cellViewB2, 1, 1);
        boardView.update();
    }

    @Test
    public void testSimulationKakoruCellsView() {
        int columns = 3;
        int rows = 2;
        GameBoard gameBoard = new GameBoard(columns, rows);
        BoardView boardView = new BoardView(gameBoard);

        CellView cellViewA1 = new KakoruCellView(5, 10);
        CellView cellViewA2 = new KakoruCellView(10, 5);
        CellView cellViewA3 = new KakoruCellView(null, null);
        CellView cellViewB1 = new KakoruCellView(null, 10);
        CellView cellViewB2 = new KakoruCellView(5, null);

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


    @Test
    public void testSimulationKakoruCellsCreation() {
        GameBoard gameBoard = new GameBoard(2, 2);

        //FixedCells are created when a KakoruCell or NullCell is parsed
        InputCell a1 = new InputCell();
        FixedCell a2 = new FixedCell(null);
        FixedCell b1 = new FixedCell(null);
        InputCell b2 = new InputCell();


        gameBoard.addCell(0, 0, a1);
        gameBoard.addCell(0, 1, a2);
        gameBoard.addCell(1, 0, b1);
        gameBoard.addCell(1, 0, b2);
        /*When KakoruCells are addede to the gameBoard, their
          respective views should be added to the boardView
        */
        BoardView boardView = new BoardView(gameBoard);
        //Cells respective views shall be created when parsing
        CellView cellViewA1 = new DataCellView(a1);
        //KakoruCellViews numbers shall be read from the config file
        //KakoruCellViews are created when a KakoruCell is parsed
        CellView cellViewA2 = new KakoruCellView(null, 8);
        CellView cellViewB1 = new KakoruCellView(22, null);
        //Same logic for NullCells
        CellView cellViewB2 = new NullCellView();
        boardView.addCellViewIn(cellViewA1, 0, 0);
        boardView.addCellViewIn(cellViewA2, 0, 1);
        boardView.addCellViewIn(cellViewB1, 1, 0);
        boardView.addCellViewIn(cellViewB2, 1, 1);

        boardView.update();

    }
}
