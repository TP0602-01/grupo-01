package ar.fiuba.tdd.tp1.view.draw.cellcomponents;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.awt.*;
import java.awt.geom.Point2D;


public interface CellViewComponent {

    public void draw(Graphics graphics, Cell cell);

}
