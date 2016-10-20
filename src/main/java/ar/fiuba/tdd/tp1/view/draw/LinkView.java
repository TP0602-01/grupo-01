package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class LinkView implements Drawable {
    private Cell origin;
    private Cell destination;
    private Graph boardGraph;
    private boolean visible;

    public LinkView(Cell origin, Cell destination, Graph boardGraph) {
        this.origin = origin;
        this.destination = destination;
        this.boardGraph = boardGraph;
        this.visible = false;
    }


    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void draw(Graphics graphics) {

        if (origin != null && destination != null && visible) {
            if (boardGraph.linkExistsFromOriginToDestination(origin, destination)) {
                drawAConnectionBetweenThem(graphics);
            }
        }
    }


    private void drawAConnectionBetweenThem(Graphics graphics) {
        graphics.drawLine(CellView.getXCenter(origin.getX()),
                CellView.getYCenter(origin.getY()),
                CellView.getXCenter(destination.getX()),
                CellView.getYCenter(destination.getY()));
    }

}

