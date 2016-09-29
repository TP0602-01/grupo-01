package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


public class WalkRow extends Walk {
    public WalkRow(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(int row, int column) {
        Vector<Cell> cells = new Vector<>();


        int currentColum = column;
        Cell cell = gameBoard.getCell(row, currentColum);
        while (cell.isWalkable()) {
            cells.add(cell);
            currentColum++;
            cell = gameBoard.getCell(row, currentColum);
        }

        return cells;
    } //TODO: ADAPTAR A KAKURO
}
