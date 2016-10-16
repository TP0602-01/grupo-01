package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;

/**
 * Created by User on 15/10/2016.
 */
public class VerticalLinksView {

    GameBoard linkableMatrix;
    Graph linksConfiguration;

    public VerticalLinksView(GameBoard gameboard, Graph linksConfiguration) {
        this.linkableMatrix = gameboard;
        this.linksConfiguration = linksConfiguration;
    }

    public String asciiDrawLinkBetween(int row, int column) {
        Cell first = this.linkableMatrix.getCell(row, column);
        Cell second = this.linkableMatrix.getCell(row + 1, column);

        if ((first != null) && (second != null)) {
            if (this.linksConfiguration.linkExistsFromOriginToDestination(first, second)) {
                return (" | ");
            }
        }
        return "    ";
    }
}
