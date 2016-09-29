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

        /*
        System.out.format("Entra al for\n");
        for (int i = row; i < gameBoard.getHeigth(); i++) {

            System.out.format("Devolviendo la posicion %d %d %n", i, column);

            Cell cell = gameBoard.getCell(i, column);
            cells.addElement(cell);
        }
*/

        int currentRow = row;
        Cell cell = gameBoard.getCell(currentRow, column);
        System.out.format("VA A ANALIZAR A %d %d %n", currentRow, column);
        while ( cell.isWalkable() ){
            System.out.format("Devolviendo la posicion %d %d %n", currentRow, column);
            cells.add(cell);
            currentRow++;
            cell = gameBoard.getCell(currentRow, column);
        }
        System.out.format("TERMINO DE ANALIZAR EN %d %d %n", currentRow, column);



        return cells;

    }

}
