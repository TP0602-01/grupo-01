package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Collection;

/*  ClustersSizesRule check if the number of edges around for each cell from graph is correct*/
public class EdgesCountRule extends Rule {
    private int expectedValue;

    public EdgesCountRule(Integer expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean check(Graph graph) {
        Collection<Cell> cells = graph.getCells();

        /* calculates cells positions */
        int up = calculateUpRow(cells);
        int down = calculateDownRow(cells);
        int right = calculateRightColumn(cells);
        int left = calculateLeftColumn(cells);

        return (calculateCount(cells, up, down, right, left) == expectedValue);
    }

    private int calculateCount(Collection<Cell> cells, int up, int down, int right, int left) {
        int count = countEdgesInCorner(cells, up, left, "\\");
        count += countEdgesInCorner(cells, down, right, "\\");
        count += countEdgesInCorner(cells, up, right, "/");
        count += countEdgesInCorner(cells, down, left, "/");
        return count;
    }

    private int countEdgesInCorner(Collection<Cell> cells, int rowIndex, int columnIndex, String token) {
        for (Cell cell : cells) {
            if (cell.getRowIndex() == rowIndex && cell.getColumnIndex() == columnIndex) {
                String cellData = cell.getData();
                if (cellData == null) {
                    return 0;
                } else {
                    return (cellData.contains(token) ? 1 : 0);
                }
            }
        }
        return 0;
    }

    private int calculateLeftColumn(Collection<Cell> cells) {
        int leftColumnIndex = Integer.MAX_VALUE;
        for (Cell cell : cells) {
            int cellColumnIndex = cell.getColumnIndex();
            if (cellColumnIndex < leftColumnIndex) {
                leftColumnIndex = cellColumnIndex;
            }
        }
        return leftColumnIndex;
    }

    private int calculateRightColumn(Collection<Cell> cells) {
        int rightColumnIndex = Integer.MIN_VALUE;
        for (Cell cell : cells) {
            int cellColumnIndex = cell.getColumnIndex();
            if (cellColumnIndex > rightColumnIndex) {
                rightColumnIndex = cellColumnIndex;
            }
        }
        return rightColumnIndex;
    }

    private int calculateDownRow(Collection<Cell> cells) {
        int downRowIndex = Integer.MIN_VALUE;
        for (Cell cell : cells) {
            int cellRowIndex = cell.getRowIndex();
            if (cellRowIndex > downRowIndex) {
                downRowIndex = cellRowIndex;
            }
        }
        return downRowIndex;
    }

    private int calculateUpRow(Collection<Cell> cells) {
        int upRowIndex = Integer.MAX_VALUE;
        for (Cell cell : cells) {
            int cellRowIndex = cell.getRowIndex();
            if (cellRowIndex < upRowIndex) {
                upRowIndex = cellRowIndex;
            }
        }
        return upRowIndex;
    }
}