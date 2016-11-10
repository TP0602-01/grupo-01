package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

/*
 * Connected Graph Count Rule check that the graph given by parameter
 * has an expected connected sub graphs count.
 *
 */
public class ConnectedGraphsCountRule extends SingleGroupRule {

    private int expectedCount;

    public ConnectedGraphsCountRule(int expectedCount) {
        this.expectedCount = expectedCount;
    }



    @Override
    public boolean check(IndexedGraph subGraph) {
        return subGraph.getConnectedSubGraphsCount() == this.expectedCount;
    }
}
