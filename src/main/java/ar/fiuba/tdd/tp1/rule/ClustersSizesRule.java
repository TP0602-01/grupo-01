package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.*;

/*  */
public class ClustersSizesRule extends Rule {
    private List<Integer> expectedValues;

    public ClustersSizesRule(List<Integer> expectedValues) {
        this.expectedValues = expectedValues;
    }

    @Override
    public boolean check(Graph graph) {
        Collection<Cell> cells = graph.getCells();
        List<Cell> cellsList = new ArrayList<>(cells);
        Collections.sort(cellsList, (c1, c2) ->
                (c1.getColumnIndex() + c1.getRowIndex())
                        - (c2.getColumnIndex() + c2.getRowIndex()));

        List<Integer> clustersSizes = getSizes(cellsList);
        return clustersSizes.equals(expectedValues);
    }

    private List<Integer> getSizes(List<Cell> cellsList) {
        List<Integer> clustersSizes = new ArrayList<>();
        int currentClusterSize = 0;

        /* foreach cell, ordered */
        for (Cell cell : cellsList) {
            currentClusterSize = calculateNewClusterSize(currentClusterSize, cell, clustersSizes);
        }

        /* if last cell is in a cluster */
        if (currentClusterSize != 0) {
            clustersSizes.add(currentClusterSize);
        }
        return clustersSizes;
    }

    private int calculateNewClusterSize(int currentClusterSize, Cell cell, List<Integer> clustersSizes) {
        if (cell.getData().equalsIgnoreCase("X")) {
            return currentClusterSize + 1;
        }
        if (currentClusterSize != 0) {
            clustersSizes.add(currentClusterSize);
        }
        return 0;
    }

}