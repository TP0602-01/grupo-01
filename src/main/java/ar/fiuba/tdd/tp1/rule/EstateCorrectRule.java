package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/*
 * EstateCorrectRule check if the circuit doesn't pass through two adjacents cells,
 * if they belong the same group
 *
 */


public class EstateCorrectRule extends Rule {

    private int boardRows;

    public EstateCorrectRule(int rows) {
        this.boardRows = rows;
    }


    @Override
    public boolean check(Queue<IndexedGraph> subgraph) {

        Queue<IndexedGraph> groups = new LinkedList<>(subgraph);

        IndexedGraph gameboardGraph = groups.remove();
        while (!groups.isEmpty()) {
            IndexedGraph cellsGroup = groups.remove();
            for (Cell cell: cellsGroup.getCells()) {
                if (cell.isEmpty()) {
                    for (Cell emptyAdyacentCell: this.getEmptyAdyacentCells(cell, gameboardGraph)) {
                        for (IndexedGraph group: groups) {
                            if (!group.contains(cell) && (group.contains(emptyAdyacentCell))) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean cellIsEmpty(Cell cell) {
        return ( (cell.getData().equals("")) || (cell.getData().equals("0")) );
    }

    private Collection<Cell> getEmptyAdyacentCells(Cell cell, IndexedGraph gameboardGraph) {
        Collection<Cell> adyacentCells = this.getAdyacentCells(cell, gameboardGraph);
        Collection<Cell> emptyAdyacentCells = new LinkedList<>();
        for (Cell adyacentCell: adyacentCells) {
            if ( (adyacentCell != null) && ( this.cellIsEmpty(adyacentCell)) ) {
                emptyAdyacentCells.add(adyacentCell);
            }
        }
        return emptyAdyacentCells;
    }

    private Collection<Cell> getAdyacentCells(Cell cell, IndexedGraph gameboardGraph) {
        Collection<Cell> adyacentCells = new LinkedList<>();
        int cellIndex = gameboardGraph.getIndexOf(cell);
        adyacentCells.add(gameboardGraph.getCell(cellIndex + 1));
        adyacentCells.add(gameboardGraph.getCell(cellIndex - 1));
        adyacentCells.add(gameboardGraph.getCell(cellIndex - this.boardRows));
        adyacentCells.add(gameboardGraph.getCell(cellIndex + this.boardRows));
        return adyacentCells;
    }


}
