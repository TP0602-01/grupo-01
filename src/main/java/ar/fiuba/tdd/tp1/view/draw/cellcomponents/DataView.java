package ar.fiuba.tdd.tp1.view.draw.cellcomponents;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.view.draw.CellView;

import java.awt.*;


public class DataView implements CellViewComponent {


    public void draw(Graphics graphics, Cell cell) {

        if (cell.getData() != null) {
            int stringWidth = graphics.getFontMetrics().stringWidth(cell.getData());
            int stringHeight = graphics.getFontMetrics().getHeight();

            graphics.drawString(cell.getData(), CellView.getXCenter(cell.getX()) - (stringWidth / 2),
                    CellView.getYCenter(cell.getY()) + (stringHeight / 3));
        }
    }

}
