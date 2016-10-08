package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkeable;

/**
 * Created by juanma on 07/10/16.
 */
public interface LinkeableMatrix {
    //ESTO ES UNA ABSTRACCION PARA QUE IMPLEMENTE EL GAMEBOARD Y LINKER NO DEPENDE DIRECTAMENTE DE GAMEBOARD
    //public Linkeable getLinkeable(int row, int column);
    //TODO:IMPORTANTE: MIENTRAS NO EXISTA LA ENTIDAD QUE MANEJE CONJUNTO DE ARISTAS LA MATRIX TIENE QUE DEVOLVER LinkeableSquares
    public Linkeable getLinkeable(int row, int column);
}
