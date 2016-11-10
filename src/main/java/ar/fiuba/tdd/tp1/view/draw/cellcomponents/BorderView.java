package ar.fiuba.tdd.tp1.view.draw.cellcomponents;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;


public class BorderView implements CellViewComponent {


    @Override
    public void draw(Graphics graphics, Cell cell) {
        int initialX = CellView.convertX(cell.getColumnIndex());
        int initialY = CellView.convertY(cell.getRowIndex());

        graphics.drawRect(initialX, initialY, CellView.width, CellView.height);

    }
}
