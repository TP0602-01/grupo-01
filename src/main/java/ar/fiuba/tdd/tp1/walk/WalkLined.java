package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

public abstract class WalkLined extends Walk {

    public WalkLined(GameBoard gameBoard) {
        super(gameBoard);
    }

    protected abstract int getNextColumn(int column);

    protected abstract int getNextRow(int row);

    public Vector<Cell> getCellList(int row, int column) {
        Vector<Cell> cells = new Vector<>();

        int currentColum = column;
        int currentRow = row;
        Cell cell = gameBoard.getCell(currentRow, currentColum);
        while (cell.isWalkable()) {
            cells.add(cell);
            currentColum = getNextColumn(currentColum);
            currentRow = getNextRow(currentRow);
            cell = gameBoard.getCell(currentRow, currentColum);
        }

        return cells;
    }
}
