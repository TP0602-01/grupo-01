package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

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

        ((Graphics2D) graphics).setStroke(new BasicStroke(border));

        drawBorderIfItMustBeDrawn(graphics, center, centers, 0, 1);
        drawBorderIfItMustBeDrawn(graphics, center, centers, 0, -1);
        drawBorderIfItMustBeDrawn(graphics, center, centers, 1, 0);
        drawBorderIfItMustBeDrawn(graphics, center, centers, -1, 0);


    }


    private void drawBorderIfItMustBeDrawn(Graphics graphics, Point2D center, Set<Point2D> centers, int offsetX, int offsetY) {
        Point2D neighbor = getNeighbor(center, cellWidth * offsetX, cellHeight * offsetY);

        if (offsetX * offsetY != 0) {
            return;
        }

        if (!centers.contains(neighbor)) {
            int initialX = getLeftBorderX((int) center.getX());
            int finalX = getRightBorderX((int) center.getX());
            int initialY = getUpperBorderY((int) center.getY());
            int finalY = getLowerBorderY((int) center.getY());


            if (offsetX == 0) {

                initialY = (int) center.getY() + offsetY * cellHeight / 2;
                finalY = initialY;
            } else {

                initialX = (int) center.getX() + offsetX * cellHeight / 2;
                finalX = initialX;
            }

            graphics.drawLine(initialX, initialY, finalX, finalY);
        }
    }


    private Point2D getNeighbor(Point2D center, int offsetX, int offsetY) {
        Point2D result = new Point((int) center.getX() + offsetX, (int) center.getY() + offsetY);
        return result;
    }


    private int getLeftBorderX(int centerX) {
        return centerX - cellWidth / 2;
    }

    private int getRightBorderX(int centerX) {
        return centerX + cellWidth / 2;
    }

    private int getUpperBorderY(int centerY) {
        return centerY - cellHeight / 2;
    }

    private int getLowerBorderY(int centerY) {
        return centerY + cellHeight / 2;
    }


    @Override
    public void draw(Graphics graphics) {
        if (border > 0) {
            Set<Point2D> centers = new HashSet<>();
            cells.forEach(cell -> centers.add(
                    new Point(CellView.getXCenter(cell.getColumnIndex()),
                            CellView.getYCenter(cell.getRowIndex()))));

            cells.forEach(cell -> drawBorders(graphics,
                    new Point(CellView.getXCenter(cell.getColumnIndex()), CellView.getYCenter(cell.getRowIndex())),
                    centers));
        }
    }
}
