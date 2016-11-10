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
    private GameBoard myGameBoard;
    private Graph myGraph;

    private int boardRows;

    public EstateCorrectRule(GameBoard gameBoard, Graph graph) {
        myGameBoard = gameBoard;
        myGraph = graph;
    }

    public EstateCorrectRule(int rows) {
        this.boardRows = rows;
    }

    private boolean checkGroup(int rowIndx, int colIndx, int rowIndxA, int colIndxA) {
        Collection<Graph> groups = myGameBoard.getCell(rowIndx, colIndx).getSets();
        Cell cell = myGameBoard.getCell(rowIndxA, colIndxA);
        for (Graph graph : groups) {
            if (graph.contains(cell)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAdjacentCell(int rowIndx, int colIndx) {
        for (int i = -1; i < 2; i++) {
            Cell adjacentCell = myGameBoard.getCell(rowIndx, colIndx + i);
            return !((adjacentCell != null)
                    && (!myGraph.cellHasConnection(adjacentCell))
                    && (!checkGroup(rowIndx, colIndx, rowIndx, colIndx + i)));
        }
        for (int i = -1; i < 2; i++) {
            Cell adjacentCell = myGameBoard.getCell(rowIndx + i, colIndx);
            return !((adjacentCell != null)
                    && (!myGraph.cellHasConnection(adjacentCell))
                    && (!checkGroup(rowIndx, colIndx, rowIndx + i, colIndx)));
        }
        return true;
    }


    private boolean adyacentCellsIsEmptyAndInSameGroup(int row, int col, Cell cell) {
        Cell adyacentCell = myGameBoard.getCell(row, col);
        if (adyacentCell == null || adyacentCell.getData() == null) {
            return false;
        }

        if (!adyacentCell.getData().equals("") && !adyacentCell.getData().equals("0")) {
            return false;
        }

        return !this.cellsBelongToTheSameSet(cell, adyacentCell);
    }


    private boolean cellsBelongToTheSameSet(Cell cell, Cell adyacentCell) {
        Collection<Graph> cellSets = cell.getSets();
        Collection<Graph> adyacentCellSets = adyacentCell.getSets();
        for (Graph cellSet : cellSets) {
            if ((adyacentCellSets.contains(cellSet))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean check(Graph graph) {
        for (int i = 0; i < myGameBoard.getWidth(); i++) {
            for (int j = 0; j < myGameBoard.getHeigth(); j++) {
                Cell cell = myGameBoard.getCell(i, j);

                if ((cell.getData().equals("") || (cell.getData().equals("0")))) {

                    if (this.adyacentCellsIsEmptyAndInSameGroup(i, j + 1, cell)
                            || this.adyacentCellsIsEmptyAndInSameGroup(i, j - 1, cell)
                            || this.adyacentCellsIsEmptyAndInSameGroup(i + 1, j, cell)
                            || this.adyacentCellsIsEmptyAndInSameGroup(i - 1, j, cell)) {
                        return false;
                    }
                }
            }
        }
        return true;
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
