package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

public class CellView implements Drawable {

    private Cell cell;
    private Collection<CellViewComponent> components;
    public static final int width = 40;
    public static final int height = 40;


    public CellView(Cell cell) {
        this.cell = cell;
        this.components = new ArrayList<>();
    }

    public void addComponent(CellViewComponent component) {
        components.add(component);
    }

    public void draw(Graphics graphics) {
        if (!(cell instanceof NullCell)) {
            components.forEach(component -> component.draw(graphics, cell));
        }
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public static int convertX(int xCoord) {
        return xCoord * width;
    }

    public static int convertY(int yCoord) {
        return yCoord * height;
    }

    public static int getXCenter(int xCoord) {
        return xCoord * width + width / 2;
    }

    public static int getYCenter(int yCoord) {
        return yCoord * height + height / 2;
    }

}
