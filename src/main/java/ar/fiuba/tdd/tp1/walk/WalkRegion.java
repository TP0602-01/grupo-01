package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


public class WalkRegion extends Walk {
    private int sizeRegion;

    public WalkRegion(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(int row, int column) {
        if (sizeRegion == 0) {
            System.out.print("Size Region is not seted%n");
            return null;
        }
        System.out.format("WalkRegion %d %d%n", row, column);
        Vector<Cell> cells = new Vector<>();
        int rowRegion = row / sizeRegion;
        int columnRegion = column / sizeRegion;
        int rowInitial = rowRegion * sizeRegion;
        int columnInitial = columnRegion * sizeRegion;
        for (int i = rowInitial; i < rowInitial + sizeRegion; i++) {
            for (int j = columnInitial; j < columnInitial + sizeRegion; j++) {
                Cell cell = gameBoard.getCell(i,j);
                cells.addElement(cell);
            }
        }
        return cells;
    }

    public void setSizeRegion(int tamRegion) {
        this.sizeRegion = tamRegion;
    }


}
