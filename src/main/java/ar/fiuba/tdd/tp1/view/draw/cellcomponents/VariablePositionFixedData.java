package ar.fiuba.tdd.tp1.view.draw.cellcomponents;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;

public class VariablePositionFixedData implements CellViewComponent {

    private double offsetX;
    private double offsetY;
    private String message;


    public VariablePositionFixedData(double offsetX, double offsetY, String message) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.message = message;
    }

    @Override
    public void draw(Graphics graphics, Cell cell) {
        int positionX = (int) (CellView.getXCenter(cell.getColumnIndex()) + offsetX * CellView.width / 2);
        int positionY = (int) (CellView.getYCenter(cell.getRowIndex()) + offsetY * CellView.height / 2);
        int stringHeight = graphics.getFontMetrics().getHeight();
        int stringWidth = graphics.getFontMetrics().stringWidth(message);
        graphics.drawString(message, positionX - stringWidth / 2, positionY + stringHeight / 3);
    }
}
