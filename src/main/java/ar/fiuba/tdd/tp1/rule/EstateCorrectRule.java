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

    /*
    public boolean check() {
        for (int i = 0; i < myGameBoard.getWidth(); i++) {
            for (int j = 0; j < myGameBoard.getHeigth(); j++) {
                Cell cell = myGameBoard.getCell(i, j);
                if (myGraph.cellHasConnection(cell)) {
                    if (!checkAdjacentCell(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    */


    @Override
    public boolean check(Graph graph) {
        for (int i = 0; i < myGameBoard.getWidth(); i++) {
            for (int j = 0; j < myGameBoard.getHeigth(); j++) {
                Cell cell = myGameBoard.getCell(i, j);
                if (graph.cellHasConnection(cell)) {
                    if (!checkAdjacentCell(i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
