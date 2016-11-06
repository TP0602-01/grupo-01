package ar.fiuba.tdd.tp1.view.draw;

import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.BorderView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.DataView;

import javax.swing.*;

public class MainCellViews {

    public static void main(String[] args) {
        JFrame frame;


        GameBoard gameBoard = new GameBoard(3, 3);
        for (int i = 0; i < gameBoard.getHeigth(); i++) {
            for (int j = 0; j < gameBoard.getWidth(); j++) {
                InputCell input = new InputCell(".");
                gameBoard.addCell(i, j, input);
                CellView drawable = new CellView(input);
                drawable.addComponent(new BorderView());
                drawable.addComponent(new DataView());

            }

        }

        frame = new JFrame("hola");
        frame.setSize(40 * 3, 40 * 4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BoardCanvas canvas = new BoardCanvas(gameBoard.getWidth(), gameBoard.getHeigth());
        frame.add(canvas);

        frame.setVisible(true);

        frame.repaint();
    }
}
