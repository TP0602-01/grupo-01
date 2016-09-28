package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

import java.util.Vector;


abstract class Walk {
    protected GameBoard gameBoard;

    Walk(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    abstract Vector<Cell> getCellList(int row, int column);
}

