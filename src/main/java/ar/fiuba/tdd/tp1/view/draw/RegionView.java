package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegionView implements Drawable {

    private int cellWidth;
    private int cellHeight;
    private Collection<Cell> cells;
    int border;

    public RegionView(Collection<Cell> cells, int border) {
        this.cellHeight = CellView.height;
        this.cellWidth = CellView.width;
        this.border = border;
        this.cells = cells;
    }

    public void drawBorders(Graphics graphics, Point2D center, Set<Point2D> centers) {
        Point2D upperNeighbor = new Point((int) center.getX(), (int) center.getY() - cellHeight);
        Point2D lowerNeighbor = new Point((int) center.getX(), (int) center.getY() + cellHeight);
        Point2D rightNeigbor = new Point((int) center.getX() + cellWidth, (int) center.getY());
        Point2D leftNeighbor = new Point((int) center.getX() - cellWidth, (int) center.getY());

        ((Graphics2D) graphics).setStroke(new BasicStroke(border));

        if (!centers.contains(upperNeighbor)) {
            drawUpperBorder(graphics, center);
        }

        if (!centers.contains(lowerNeighbor)) {
            drawLowerBorder(graphics, center);
        }

        if (!centers.contains(rightNeigbor)) {
            drawRightBorder(graphics, center);
        }

        if (!centers.contains(leftNeighbor)) {
            drawLeftBorder(graphics, center);
        }

    }

    private void drawLeftBorder(Graphics graphics, Point2D center) {
        int borderX = (int) center.getX() - cellWidth / 2;
        int initialY = (int) center.getY() - cellHeight / 2;


        graphics.drawLine(borderX, initialY, borderX, initialY + cellHeight);

    }

    private void drawRightBorder(Graphics graphics, Point2D center) {
        int borderX = (int) center.getX() + cellWidth / 2;
        int initialY = (int) center.getY() - cellHeight / 2;

        graphics.drawLine(borderX, initialY, borderX, initialY + cellHeight);
    }


    private void drawUpperBorder(Graphics graphics, Point2D center) {
        int initialX = (int) center.getX() - cellWidth / 2;
        int borderY = (int) center.getY() - cellHeight / 2;

        graphics.drawLine(initialX, borderY, initialX + cellWidth, borderY);
    }

    private void drawLowerBorder(Graphics graphics, Point2D center) {
        int initialX = (int) center.getX() - cellWidth / 2;
        int borderY = (int) center.getY() + cellHeight / 2;

        graphics.drawLine(initialX, borderY, initialX + cellWidth, borderY);
    }


    @Override
    public void draw(Graphics graphics) {
        Set<Point2D> centers = new HashSet<>();
        cells.forEach(cell -> centers.add(
                new Point(CellView.getXCenter(cell.getX()),
                        CellView.getYCenter(cell.getY()))));

        cells.forEach(cell -> drawBorders(graphics,
                new Point(CellView.getXCenter(cell.getX()), CellView.getYCenter(cell.getY())),
                centers));
    }
}
