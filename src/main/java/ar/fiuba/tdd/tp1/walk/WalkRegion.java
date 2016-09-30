package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


public class WalkRegion extends Walk {
    private int sizeRegion;

    public WalkRegion(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getCellList(String description) {
        String row = description.split("-")[0];
        String column = description.split("-")[1];

        int rowI = Integer.parseInt(row.split(",")[0]);
        
        int columnI = Integer.parseInt(row.split(",")[1]);
        int rowE = Integer.parseInt(column.split(",")[0]);
        int columnE = Integer.parseInt(column.split(",")[1]);
        Vector<Cell> cells = new Vector<>();
        for (int rowIdx = rowI; rowIdx <= rowE  ; ++rowIdx) {
            for (int columnIdx = columnI; columnIdx <= columnE; ++columnIdx) {
                Cell cell = gameBoard.getCell(rowIdx,columnIdx);
                cells.add(cell);
            }
        }
        return cells;
    }

    public void setSizeRegion(int tamRegion) {
        this.sizeRegion = tamRegion;
    }


}
