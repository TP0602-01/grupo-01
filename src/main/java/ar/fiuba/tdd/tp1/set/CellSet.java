package ar.fiuba.tdd.tp1.set;

import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;
import ar.fiuba.tdd.tp1.rule.Rule;

import java.util.*;

/*
 * Cell Set have a collection of rules to aply to a graph of cells
 *
 */
public class CellSet {
    private Collection<Rule> rules;
    private Queue<IndexedGraph> subGraphs;

    public CellSet(Queue<IndexedGraph> subgraphs, Collection<Rule> rules) {
        this.subGraphs = subgraphs;
        this.rules = rules;
    }

    private void updateSubgraphs() {
        for ( IndexedGraph subgraph: this.subGraphs) {
            subgraph.updateSubGraphLinks();
        }
    }

    public boolean check() {
        this.updateSubgraphs();
        boolean check = true;
        Iterator it = rules.iterator();
        while (it.hasNext()) {
            Rule rule = (Rule) it.next();

            if (!rule.check(this.subGraphs)) {
                check = false;
            }

        }
        return check;
    }
}
