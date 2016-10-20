package ar.fiuba.tdd.tp1.view.draw;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.BorderView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CornerView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.DataView;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;

public class MainCellViews {

    public static void main(String[] args) {
        Collection<Pair<Point2D,Cell>> cells = new ArrayList<>();
        JFrame frame;


        GameBoard gameBoard = new GameBoard(3, 3);
        BoardCanvas canvas = new BoardCanvas(gameBoard.getWidth(),gameBoard.getHeigth());
        for (int i = 0; i < gameBoard.getHeigth(); i++) {
            for (int j = 0; j < gameBoard.getWidth(); j++) {
                InputCell input = new InputCell("."); //new InputCell(Integer.toString(i));
                gameBoard.addCell(i, j, input);
                Point2D center = new Point(40 * i + 20, 40 * j + 20);
                CellView drawable = new CellView(input);
                drawable.addComponent(new BorderView());
                drawable.addComponent(new DataView());
                drawable.addComponent(new DataView());

                if ( i == 0  && j == 0) {
                    cells.add(new Pair<>(center,input));
                }
                if (j == 1){

                    cells.add(new Pair<>(center,input));
                }

                //canvas.addDrawable(drawable);
            }

        }
        //canvas.addDrawable(new RegionView(cells,40,40,10));

        frame = new JFrame("hola");
        frame.setSize(40*3,40*4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);

        frame.setVisible(true);

        frame.repaint();
    }
}
