package ar.fiuba.tdd.tp1.view.draw.cellcomponents;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;
import java.awt.geom.Point2D;


public class BorderView implements CellViewComponent {


    @Override
    public void draw(Graphics graphics, Cell cell) {
        int xIni = CellView.convertX(cell.getX());
        int yIni = CellView.convertY(cell.getY());

        graphics.drawRect(xIni, yIni, CellView.width, CellView.height);

    }
}
