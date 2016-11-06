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

        List<Integer> clustersSizes = new ArrayList<>();
        int currentClusterSize = 0;
        for (Cell cell : cellsList) {
            if (cell.getData().equalsIgnoreCase("X")) {
                ++currentClusterSize;
            } else if (currentClusterSize != 0) {
                clustersSizes.add(currentClusterSize);
                currentClusterSize = 0;
            }
        }
        if (currentClusterSize != 0) {
            clustersSizes.add(currentClusterSize);
        }

        return clustersSizes.equals(expectedValues);
    }

}