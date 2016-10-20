package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;

/* */
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

        if ((first != null) && (second != null) && this.linksConfiguration.linkExistsFromOriginToDestination(first, second)) {
            return ("----");
        }
        return "    ";
    }
}
