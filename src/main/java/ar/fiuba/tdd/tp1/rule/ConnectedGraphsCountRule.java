package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.Rule;

/*
 * Connected Graph Count Rule check that the graph given by parameter
 * has an expected connected sub graphs count.
 *
 */
public class ConnectedGraphsCountRule extends Rule {

    private int expectedCount;

    public ConnectedGraphsCountRule(int expectedCount) {
        this.expectedCount = expectedCount;
    }


    @Override
    public boolean check(Graph graph) {
        return Graph.getSingleInstance().getConnectedSubGraphsCount() == this.expectedCount;
    }
}
