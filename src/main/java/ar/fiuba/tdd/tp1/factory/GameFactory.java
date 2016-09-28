package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;

import java.util.ArrayList;
import java.util.Collection;

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


    /* TODO: just for sudoku */
    //TODO: POR ALGUNA RAZON ME TIRABA WARNINGS EL I,J
    private static final Collection<Cell> calculateRegion(GameBoard board, int i1, int j1) {
        Collection<Cell> cells = new ArrayList<>();
        for (int k = 0; k < 3; ++k) {
            for (int l = 0; l < 3; ++l) {
                cells.add(board.getCell(3 * i1 + k, 3 * j1 + l));
            }
        }
        return cells;
    }

    /* TODO: just for sudoku */
    private static final void addRegions(GameBoard board) {
        for (int idxX = 0; idxX < 3; ++idxX) {
            for (int idxY = 0; idxY < 3; ++idxY) {
                board.addRule(new NoRepetitionRule(calculateRegion(board, idxX, idxY)));
            }
        }
    }

    private static final void addRows(GameBoard board) {
        for (int row = 0; row < 9; ++row) {
            Collection<Cell> cells = new ArrayList<>();
            for (int col = 0; col < 9; ++col) {
                cells.add(board.getCell(row, col));
            }
            board.addRule(new NoRepetitionRule(cells));
        }
    }

    private static final void addCols(GameBoard board) {
        for (int col = 0; col < 9; ++col) {
            Collection<Cell> cells = new ArrayList<>();
            for (int row = 0; row < 9; ++row) {
                cells.add(board.getCell(row, col));
            }
            board.addRule(new NoRepetitionRule(cells));
        }
    }


    public void loadRestrictions(GameBoard board) {
        /* TODO: foreach "new", find the corresponding restriction factory in a dictionary
         * and invoke its creator method */

        /* SUDOKU */
        addRegions(board);
        addRows(board);
        addCols(board);
    }
}
