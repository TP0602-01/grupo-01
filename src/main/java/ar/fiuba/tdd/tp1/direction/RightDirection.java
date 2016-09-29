package ar.fiuba.tdd.tp1.direction;

import ar.fiuba.tdd.tp1.cell.Cell;

/* */
public class RightDirection implements Direction {

    @Override
    public Cell getNextCell(Cell cell) {
        return cell;
    }
}
