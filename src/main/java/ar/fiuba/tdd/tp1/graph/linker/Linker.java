package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Set;

/* */
public interface Linker {

    void updateLinkableLinks(int row, int column);

    Graph getGraph();
}
