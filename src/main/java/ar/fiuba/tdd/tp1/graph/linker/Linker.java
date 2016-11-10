package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.Graph;

import java.util.Set;


/*
* Linker has the responsibility of comparing the data of different Cells and link them
* if their data is compatible
*/
public interface Linker {
    
    void updateLinkableLinks(int row, int column);

    Graph getGraph();
}
