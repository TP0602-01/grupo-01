package ar.fiuba.tdd.tp1.gameboard;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linker.LinkableMatrix;
import ar.fiuba.tdd.tp1.rule.Rule;
import ar.fiuba.tdd.tp1.utilities.Observable;

import java.util.*;

/*
 * Game Board represent a cell matrix. It is used to insert and get cells
 * into it and to set content values into cells.
 *
 */
public class GameBoard extends Observable implements LinkableMatrix {

    private Map<Integer, Map<Integer, Cell>> cells;
    private Graph cellsLinks;

    public GameBoard(Integer width, Integer height) {
        cells = new HashMap<>();
        for (int rowIdx = 0; rowIdx < height; ++rowIdx) {
            Map<Integer, Cell> rowCells = new HashMap<>();
            for (int colIdx = 0; colIdx < width; ++colIdx) {
                rowCells.put(colIdx, new NullCell());
            }
            cells.put(rowIdx, rowCells);
        }

        this.cellsLinks = new Graph();
    }

    public void addCell(int rowIdx, int columnIdx, Cell cell) {

        cells.get(rowIdx).put(columnIdx, cell);
        cell.setPosition(rowIdx, columnIdx);
    }

    public Cell getCell(int rowIdx, int columnIdx) {
        if ((rowIdx >= this.getHeigth()) || (columnIdx >= this.getWidth())
                || (rowIdx < 0) || (columnIdx < 0)) {
            return new NullCell();
        } else {
            return cells.get(rowIdx).get(columnIdx);
        }
    }

    public void setCellValue(int rowIdx, int columnIdx, String value) {
        Cell chosenCell = this.getCell(rowIdx, columnIdx);
        chosenCell.setData(value);
        updateObservers();
    }

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


    public Graph getCellsLinks() {
        return this.cellsLinks;
    }


    public IndexedGraph getSubgraph(Collection<Cell> subgraphCells) {
        return new IndexedGraph(subgraphCells, this.cellsLinks);
    }
}
