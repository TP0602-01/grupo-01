package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
 * No Repetition Rule Check that the all cells in set
 * have a distinct content.
 *
 */
public class NoRepetitionRule extends SingleGroupRule {

    public NoRepetitionRule() {
    }

    public boolean check(Graph graph) {
        Collection<Cell> cells = graph.getCells();

        boolean validRule = true;
        Set<Integer> cellSet = new HashSet<>();
        for (Cell cell : cells) {
            if (!cellSet.add(cell.getDataAsInteger())) {
                validRule = false;
            }
        }
        return validRule;
    }

    @Override
    public boolean check(IndexedGraph subGraph) {
        Collection<Cell> cells = subGraph.getCells();

        boolean validRule = true;
        Set<Integer> cellSet = new HashSet<>();
        for (Cell cell : cells) {
            if (!cellSet.add(cell.getDataAsInteger())) {
                validRule = false;
            }
        }
        return validRule;
    }
}
