package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


public class WalkRegion extends Walk {
    private int sizeRegion;

    public WalkRegion(GameBoard gameBoard) {
        super(gameBoard);
    }

    public Vector<Cell> getRegion(String posI, String posE){
        int rowI = Integer.parseInt(posI.split(",")[0]);
        int columnI = Integer.parseInt(posI.split(",")[1]);
        int rowE = Integer.parseInt(posE.split(",")[0]);
        int columnE = Integer.parseInt(posE.split(",")[1]);
        Vector<Cell> cells = new Vector<>();
        for (int row = rowI; row <= rowE  ; row++){
            for (int column = columnI; column<=columnE; column++){
                Cell cell = gameBoard.getCell(row,column);
                cells.add(cell);
            }
        }
    }

    public void setSizeRegion(int tamRegion) {
        this.sizeRegion = tamRegion;
    }


}
