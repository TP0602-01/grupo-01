package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.walk.Walk;
import ar.fiuba.tdd.tp1.walk.WalkColumn;
import ar.fiuba.tdd.tp1.walk.WalkRegion;
import ar.fiuba.tdd.tp1.walk.WalkRow;

/* */
public class WalkFactory {
    public static Walk create(GameBoard board, String walk) {
        if (walk.equals("row")) {
            return new WalkRow(board);
        } else if (walk.equals("col")) {
            return new WalkColumn(board);
        } else if (walk.equals("region")) {
            WalkRegion walkRegion = new WalkRegion(board);
            return walkRegion;
        }
        return null;
    }
}
