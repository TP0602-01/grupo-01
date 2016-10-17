package ar.fiuba.tdd.tp1.gameboard;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linker.LinkableMatrix;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.utilities.Observable;

import java.util.*;

/*  */
public class GameBoard extends Observable implements LinkableMatrix {

    private Map<Integer, Map<Integer, Cell>> cells;

    private Collection<Rule> rules;

    public GameBoard(Integer width, Integer height) {
        rules = new ArrayList<>();

        cells = new HashMap<>();
        for (int rowIdx = 0; rowIdx < height; ++rowIdx) {
            Map<Integer, Cell> rowCells = new HashMap<>();
            for (int colIdx = 0; colIdx < width; ++colIdx) {
                rowCells.put(colIdx, new NullCell());
            }
            cells.put(rowIdx, rowCells);
        }
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }

    public void addCell(int rowIdx, int columnIdx, Cell cell) {
        cells.get(rowIdx).put(columnIdx, cell);
        updateObservers();
    }

    public Cell getCell(int rowIdx, int columnIdx) {
        if ((rowIdx >= this.getHeigth()) || (columnIdx >= this.getWidth())
                || (rowIdx < 0) || (columnIdx < 0)) {
            return null;//new NullCell();
        } else {
            return cells.get(rowIdx).get(columnIdx);
        }

    }


    public void setCellValue(int rowIdx, int columnIdx, String value) {
        Cell chosenCell = this.getCell(rowIdx, columnIdx);
        chosenCell.setData(value);
        this.updateObservers();
    }

    public boolean isFull() {
        int width = getWidth();
        int heigth = getHeigth();
        for (int i = 0; i < heigth; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = getCell(i, j);
                if (cell.isEmpty()) {
                    return false;

                }
            }
        }
        return true;
    }


    /*public boolean checkRules() {
        boolean result = true;
        for (IRule rule : rules) {
            result &= rule.check();
        }
        return result;
    }*/

    public int getHeigth() {
        return cells.size();
    }

    public int getWidth() {
        return cells.get(0).size();
    }


    @Override
    public Linkable getLinkable(int row, int column) {
        return this.getCell(row, column);
    }


}
