package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Collection;

public class EstateCorrectRule extends Rule {
    private GameBoard myGameBoard;
    private Graph myGraph;

    public EstateCorrectRule(GameBoard gameBoard, Graph graph) {
        myGameBoard = gameBoard;
        myGraph = graph;
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
}
