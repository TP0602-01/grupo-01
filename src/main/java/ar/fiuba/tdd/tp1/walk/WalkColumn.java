package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;

/**
 */
public class WalkColumn extends Walk {
    public WalkColumn(GameBoard game_board){
        super(game_board);
    }
    public Vector<Cell> getCellList(int row, int column){
        System.out.print("WalkColumn\n");
        Vector<Cell> cells = new Vector<>();
        for(int i = 0; i < _gameBoard.getHeigth(); i++){
            System.out.format("Devolviendo el elemento %d %d \n",i,column);
        }
        return cells;
    }
}
