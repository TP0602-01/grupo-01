package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

/**
 */
public class WalkRow extends Walk{
    public WalkRow(GameBoard game_board){
        super(game_board);
    }
    public Vector<Cell> getCellList(int row, int column){
        System.out.format("WalkRow %d %d\n",row,column);
        Vector<Cell> cells = new Vector<>();
        for(int i = 0; i < _gameBoard.getWidth(); i++){
            System.out.format("Devolviendo el elemento %d %d\n",row,i);
            /*Cell cell = _gameBoard.getCell(row,i);
            cells.insertElementAt(cell,i);*/
        }
        return cells;
    }
}
