package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

/**
 */
public class WalkRegion extends Walk {
    private int _sizeRegion;
    public WalkRegion(GameBoard gameBoard){
        super(gameBoard);
    }
    public Vector<Cell> getCellList(int row,int column){
        if (_sizeRegion == 0){
            System.out.print("Size Region is not seted\n");
            return null;
        }
        System.out.format("WalkRegion %d %d\n",row,column);
        Vector<Cell> cells = new Vector<>();
        int rowRegion = row / _sizeRegion;
        int columnRegion = column /_sizeRegion;
        int rowInitial = rowRegion*_sizeRegion;
        int columnInitial = columnRegion*_sizeRegion;
        for(int i = rowInitial; i < rowInitial+ _sizeRegion;i++ ){
            for(int j = columnInitial; j < columnInitial+ _sizeRegion;j++){
                System.out.format("Devolviendo las posisiones %d %d\n",i,j);
            }
        }
        return cells;
    }

    public void setSizeRegion(int tamRegion){
        _sizeRegion = tamRegion;
    }


}
