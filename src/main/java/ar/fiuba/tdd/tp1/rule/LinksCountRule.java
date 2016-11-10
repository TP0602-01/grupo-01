package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.ArrayList;

/*
 * Links Rule check that the graph given by parameter
 * has an expected quantity links beetwen its cells.
 *
 */
public class LinksCountRule extends SingleGroupRule {

    private int expectedLinksCount;

    public LinksCountRule(int expectedLinksCount) {
        this.expectedLinksCount = expectedLinksCount;
    }

    @Override
    public boolean check(IndexedGraph subGraph) {
        return subGraph.getNotDirectedLinksCount() == this.expectedLinksCount;
    }
}
