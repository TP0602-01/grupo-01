package ar.fiuba.tdd.tp1.graphSecondIt.linker;

import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public interface SquareLinker {

    //El SquareLinker se comunica con un squareGraph o rectangleGraph
    public void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens);
    public void updateLinkeablesLinks(int row, int column);
}
