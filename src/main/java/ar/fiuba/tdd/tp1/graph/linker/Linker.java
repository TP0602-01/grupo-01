package ar.fiuba.tdd.tp1.graph.linker;

import java.util.Set;

/* */
public interface Linker {

    //El Linker se comunica con un squareGraph o rectangleGraph
    void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens);

    void updateLinkableLinks(int row, int column);
}