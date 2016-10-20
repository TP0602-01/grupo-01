package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by juanma on 20/10/16.
 */
public class LinksCountRule extends Rule{

    private int expectedLinksCount;

    public LinksCountRule(int expectedLinksCount) {
        this.expectedLinksCount = expectedLinksCount;
    }

    @Override
    public boolean check(Graph graph) {
        int count = 0;

        ArrayList<Cell> cellsArray = new ArrayList<>(graph.getCells());

        for (int i = 0; i < cellsArray.size(); i++) {
            Cell originCell = cellsArray.get(i);
            for (int j = i + 1; j < cellsArray.size(); j++) {
                Cell destinationCell = cellsArray.get(j);
                Graph globalGraph = Graph.getSingleInstance();
                if (globalGraph.linkExistsFromOriginToDestination(originCell, destinationCell)) {
                    count++;
                }
            }
        }

        return (count == this.expectedLinksCount);
    }
}
