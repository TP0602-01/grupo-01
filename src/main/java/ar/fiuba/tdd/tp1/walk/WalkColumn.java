package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;

public class WalkColumn extends WalkLined {

    public WalkColumn(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    protected int getNextColumn(int column) {
        return column;
    }

    @Override
    protected int getNextRow(int row) {
        return row + 1;
    }

}
