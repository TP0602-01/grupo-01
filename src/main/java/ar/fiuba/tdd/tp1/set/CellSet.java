package ar.fiuba.tdd.tp1.set;

import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.Rule;

import java.util.Collection;
import java.util.Iterator;

/*
 * Cell Set have a collection of rules to aply to a graph of cells
 */
public class CellSet {
    private Graph graph;
    private Collection<Rule> rules;

    public CellSet(Graph graph, Collection<Rule> rules) {
        this.graph = graph;
        this.rules = rules;
    }

    public boolean check() {
        boolean check = true;
        Iterator it = rules.iterator();
        while ( it.hasNext() ) {
            Rule rule = (Rule) it.next();
            if (!rule.check(graph)) {
                check = false;
            }
        }
        return check;
    }
}
