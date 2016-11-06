package ar.fiuba.tdd.tp1.view.draw.cellcomponents;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;
import java.awt.geom.Point2D;

public class CornerView implements CellViewComponent {

    private Point2D offset;
    private String message;

    public CornerView(Point2D offset, String message) {
        this.offset = offset;
        this.message = message;
    }

    @Override
    public void draw(Graphics graphics, Cell cell) {
        int positionX = (int) (CellView.getXCenter(cell.getX()) + offset.getX() * CellView.width / 2);
        int positionY = (int) (CellView.getYCenter(cell.getY()) + offset.getY() * CellView.height / 2);
        int stringHeight = graphics.getFontMetrics().getHeight();
        int stringWidth = graphics.getFontMetrics().stringWidth(message);
        graphics.drawString(message, positionX - stringWidth / 2, positionY + stringHeight / 3);
    }
}
