package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

public class WalkColumn extends Walk {

    public WalkColumn(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(int row, int column) {
        Vector<Cell> cells = new Vector<>();

        for (int i = row; i < gameBoard.getHeigth(); i++) {
            try {
                InputCell cell = (InputCell) gameBoard.getCell(i, column);
                cells.addElement(cell);
            } catch (Exception e) {
                break;
            }
        }
        return cells;
    }

}
