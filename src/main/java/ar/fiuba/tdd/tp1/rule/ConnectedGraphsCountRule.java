package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.Rule;

public class ConnectedGraphsCountRule extends Rule {

    private int expectedCount;

    public ConnectedGraphsCountRule(int expectedCount) {
        this.expectedCount = expectedCount;
    }


    @Override
    public boolean check(Graph graph) {
        //return graph.getConnectedSubGraphsCount() == this.expectedCount;
        return Graph.getSingleInstance().getConnectedSubGraphsCount() == this.expectedCount;
    }
}
