package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;

/* */
public interface LinkableMatrix {

    //ESTO ES UNA ABSTRACCION PARA QUE IMPLEMENTE EL GAMEBOARD Y LINKER NO DEPENDA DIRECTAMENTE DE GAMEBOARD
    Linkable getLinkable(int row, int column);
}
