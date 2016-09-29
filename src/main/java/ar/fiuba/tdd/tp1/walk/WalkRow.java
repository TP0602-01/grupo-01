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


        int currentColum = column;
        Cell cell = gameBoard.getCell(row, currentColum);
        System.out.format("VA A ANALIZAR A %d %d %n", row, currentColum);
        while ( cell.isWalkable() ){
            System.out.format("Devolviendo la posicion %d %d %n", row, currentColum);
            cells.add(cell);
            currentColum++;
            cell = gameBoard.getCell(row, currentColum);
        }
        System.out.format("TERMINO DE ANALIZAR EN %d %d %n", row, currentColum);

        return cells;
    } //TODO: ADAPTAR A KAKURO
}
