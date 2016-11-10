package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;

import java.awt.*;

/**
 * LinkView is the graphical representation of a Link between two cells.
 */

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

        if (origin != null && destination != null && visible
                && boardGraph.linkExistsFromOriginToDestination(origin, destination)) {
            drawAConnectionBetweenThem(graphics);

        }
    }


    private void drawAConnectionBetweenThem(Graphics graphics) {
        graphics.drawLine(CellView.getXCenter(origin.getColumnIndex()),
                CellView.getYCenter(origin.getRowIndex()),
                CellView.getXCenter(destination.getColumnIndex()),
                CellView.getYCenter(destination.getRowIndex()));
    }

}

