package ar.fiuba.tdd.tp1.gameboard;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.utilities.Observable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

/*  */
public class GameBoard extends Observable {
    private Vector<Vector<Cell>> cells;
    private Collection<IRule> rules;

    public GameBoard(Integer width, Integer height) {
        rules = new ArrayList<>();
        cells = new Vector<>(height);
        for (int rowIdx = 0; rowIdx < height; ++rowIdx) {
            cells.insertElementAt(new Vector<Cell>(width), rowIdx);
        }
    }

    public void addRule(IRule rule) {
        rules.add(rule);
    }

    public void addCell(int rowIdx, int columnIdx, Cell cell) {
        cells.elementAt(rowIdx).insertElementAt(cell, columnIdx);
    }

    public Cell getCell(int rowIdx, int columnIdx) {
        return cells.elementAt(rowIdx).elementAt(columnIdx);
    }


    public void setCellValue(int rowIdx, int columnIdx, Integer value) {
        cells.elementAt(rowIdx).elementAt(columnIdx).setData(value);
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


    public boolean checkRules() {
        boolean result = true;
        for (IRule rule : rules) {
            result &= rule.check();
        }
        return result;
    }

    public int getHeigth() {
        return cells.capacity();    //TODO: CHEQUEAR
    }

    public int getWidth() {
        return cells.elementAt(0).capacity();
    }
}
