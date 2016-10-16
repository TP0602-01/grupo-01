package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linker.LinkableMatrix;

/**
 * Created by User on 15/10/2016.
 */
public class HorizontalLinksView {

    GameBoard linkableMatrix;
    Graph linksConfiguration;

    public HorizontalLinksView(GameBoard gameboard, Graph linksConfiguration) {
        this.linkableMatrix = gameboard;
        this.linksConfiguration = linksConfiguration;
    }

    public String asciiDrawLinkBetween(int rowFirst, int columnFirst, int rowSecond, int columnSecond) {
        Cell first = this.linkableMatrix.getCell(rowFirst, columnFirst);
        Cell second = this.linkableMatrix.getCell(rowSecond, columnSecond);

        if ( (first != null) && (second != null) ) {
            if (this.linksConfiguration.linkExistsFromOriginToDestination(first, second)) {
                return ("----");
            }
        }
        return "    ";
    }
}
