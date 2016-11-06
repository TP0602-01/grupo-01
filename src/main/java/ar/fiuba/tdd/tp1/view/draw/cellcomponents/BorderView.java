package ar.fiuba.tdd.tp1.view.draw.cellcomponents;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;
import java.awt.geom.Point2D;


public class BorderView implements CellViewComponent {


    @Override
    public void draw(Graphics graphics, Cell cell) {
        int initialX = CellView.convertX(cell.getX());
        int initialY = CellView.convertY(cell.getY());

        graphics.drawRect(initialX, initialY, CellView.width, CellView.height);

    }
}
