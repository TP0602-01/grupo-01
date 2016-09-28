package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

public class WalkColumn extends Walk {

    public WalkColumn(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(int row, int column) {
        System.out.print("WalkColumn%n");
        Vector<Cell> cells = new Vector<>();
        for (int i = 0; i < gameBoard.getHeigth(); i++) {
            Cell cell = gameBoard.getCell(i,column);
            cells.addElement(cell);
        }
        return cells;
    }

}
