package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Set;

/* */
public interface Linker {

    /*The linker communicates with un squeareGraph or rectangleGraph*/
    void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens);

    void updateLinkableLinks(int row, int column);

    Graph getGraph();
}
