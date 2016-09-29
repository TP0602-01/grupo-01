package ar.fiuba.tdd.tp1.walk;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;

public class WalkRow extends WalkLined {

    public WalkRow(GameBoard gameBoard) {
        super(gameBoard);
    }

    @Override
    protected int getNextColumn(int column) {
        return column + 1;
    }

    @Override
    protected int getNextRow(int row) {
        return row;
    }
}
