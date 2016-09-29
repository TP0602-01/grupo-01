package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;

public class GameFactory {

    public GameFactory() {
    }

    public GameBoard createGameBoard(int width, int height) {
        /* TODO: foreach "new", find the corresponding cell factory in a dictionary
         * and invoke its creator method */
        GameBoard board = new GameBoard(width, height);
        for (int rowIdx = 0; rowIdx < width; ++rowIdx) {
            for (int columnIdx = 0; columnIdx < height; ++columnIdx) {
                board.addCell(rowIdx, columnIdx, new InputCell());
            }
        }
        return board;
    }
}