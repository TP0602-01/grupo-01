package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;

/* */
public interface LinkableMatrix {

    //Segregation interface to Graph
    Linkable getLinkable(int row, int column);
}
