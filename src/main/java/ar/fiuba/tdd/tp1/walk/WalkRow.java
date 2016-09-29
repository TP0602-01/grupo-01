package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


public class WalkRow extends Walk {
    public WalkRow(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(int row, int column) {
        System.out.format("WalkRow %d %d%n", row, column);
        Vector<Cell> cells = new Vector<>();

        for (int i = 0; i < gameBoard.getWidth(); i++) {
            System.out.format("Devolviendo la posicion %d %d %n", row, i);
            Cell cell = gameBoard.getCell(row, i);
            cells.add(cell);
        }
        return cells;
    } //TODO: ADAPTAR A KAKURO
}
