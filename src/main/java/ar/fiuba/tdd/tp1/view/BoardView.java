package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.utilities.Observer;
import ar.fiuba.tdd.tp1.view.draw.BoardCanvas;
import ar.fiuba.tdd.tp1.view.draw.CellView;
import ar.fiuba.tdd.tp1.view.draw.Drawable;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import javax.swing.*;

public class BoardView extends Observer {


    private static final int extraY = 30;
    private GameBoard gameBoard;

    private int width;
    private int height;
    private BoardCanvas canvas;
    private JFrame frame;


    public BoardView(GameBoard gameBoard) {
        this.canvas = new BoardCanvas(gameBoard.getWidth(), gameBoard.getHeigth());
        this.gameBoard = gameBoard;
        this.frame = new JFrame();
        this.gameBoard.registerObserver(this);

        this.width = gameBoard.getWidth();
        this.height = gameBoard.getHeigth();

        int frameWidth = gameBoard.getWidth() * CellView.width;
        int frameHeight = gameBoard.getHeigth() * CellView.height;

        initializeCellViews(gameBoard);

        if (frameHeight > frameWidth) {
            frameWidth = frameHeight;
        } else {
            frameHeight = frameWidth;
        }

        frame.setSize(frameWidth, frameHeight + extraY);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.setVisible(true);
    }

    private void initializeCellViews(GameBoard gameBoard) {
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                this.setCell(x,y,gameBoard.getCell(y,x));
            }
        }

    }


    public void initializeLinkViews(boolean drawLinks, Graph graph) {
        for (int x = 0; x <= gameBoard.getWidth(); ++x) {
            for (int y = 0; y <= gameBoard.getHeigth(); ++y) {
                Cell cello = gameBoard.getCell(x, y);
                if (x + 1 < gameBoard.getWidth()) {
                    Cell destination = gameBoard.getCell(x + 1, y);
                    addLinkView(cello, destination, graph, drawLinks);
                }
                if (y + 1 < gameBoard.getHeigth()) {
                    Cell destination = gameBoard.getCell(x, y + 1);
                    addLinkView(cello, destination, graph, drawLinks);
                }

            }
        }
    }


    @Override
    public void update() {
        this.frame.repaint();
    }

    public void addDrawable(Drawable drawable) {
        this.canvas.addDrawable(drawable);
    }

    public void addLinkView(Cell origin, Cell destination, Graph graph, boolean visible) {
        this.canvas.addLinkView(origin, destination, graph);
        this.canvas.getLinkView(origin, destination).setVisible(visible);

    }

    public void addCellComponent(int row, int column, CellViewComponent component) {
        this.canvas.addCellComponent(row, column, component);
    }

    public void setCell(int row, int column, Cell cell) {
        CellView cellView = this.canvas.getCellView(row, column);
        cellView.setCell(cell);
    }


    public CellView getCellView(int row, int column) {
        return canvas.getCellView(row, column);
    }


    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

}
