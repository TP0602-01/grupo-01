package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.utilities.Observer;
import ar.fiuba.tdd.tp1.view.draw.BoardCanvas;
import ar.fiuba.tdd.tp1.view.draw.CellView;
import ar.fiuba.tdd.tp1.view.draw.Drawable;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import javax.swing.*;
import java.util.*;

public class BoardView extends Observer {
    /*
    private Map<Integer, Map<Integer, CellView>> cellViews;
    private HorizontalLinksView horizontalLinksView;
    private VerticalLinksView verticalLinksView;
    */

    private final int yAddition = 30;
    private GameBoard gameBoard;


    private BoardCanvas canvas;
    private JFrame frame;


    public BoardView(GameBoard gameBoard) {
        this.canvas = new BoardCanvas(gameBoard.getWidth(), gameBoard.getHeigth());
        this.gameBoard = gameBoard;
        this.frame = new JFrame();
        this.gameBoard.registerObserver(this);

        int frameWidth = gameBoard.getWidth() * CellView.width;
        int frameHeight = gameBoard.getHeigth() * CellView.height;

        if (frameHeight > frameWidth) {
            frameWidth = frameHeight;
        } else {
            frameHeight = frameWidth;
        }

        frame.setSize(frameWidth, frameHeight + yAddition);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(canvas);
        frame.setVisible(true);
    }


    @Override
    public void update() {
        this.frame.repaint();
    }

//    public void addDrawable(Drawable drawable) {
//        this.canvas.addDrawable(drawable);
//    }

    public void addLinkView(Cell origin, Cell destination, Graph graph) {
        this.canvas.addLinkView(origin, destination, graph);
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

/*
    public BoardView(GameBoard gameBoard) {

        this.canvas = new BoardCanvas();

        this.gameBoard = gameBoard;
        this.gameBoard.registerObserver(this);

        cellViews = new HashMap<>();
        for (int rowIdx = 0; rowIdx < this.getRowsNumber(); ++rowIdx) {
            Map<Integer, CellView> rowCellsViews = new HashMap<>();
            for (int colIdx = 0; colIdx < this.getColumnsNumber(); ++colIdx) {
                rowCellsViews.put(colIdx, new NullCellView());
            }
            cellViews.put(rowIdx, rowCellsViews);
        }
    }


    public BoardView(GameBoard gameBoard, HorizontalLinksView horizontalLinksView) {
        this(gameBoard);
        //this.horizontalLinksView = horizontalLinksView;

    }


    public BoardView(GameBoard gameBoard, HorizontalLinksView horizontalLinksView, VerticalLinksView verticalLinksView) {
        this(gameBoard, horizontalLinksView);
        this.verticalLinksView = verticalLinksView;
    }


    @Override
    public void update() {

        String rowSeparatorLine = "";

        for (int i = 0; i < this.getColumnsNumber(); i++) {
            rowSeparatorLine = rowSeparatorLine.concat("-------");
        }
        for (int rowIdx = 0; rowIdx < this.getRowsNumber(); ++rowIdx) {
            System.out.println(rowSeparatorLine);

            String rowAscii = "";
            for (int colIdx = 0; colIdx < this.getColumnsNumber(); ++colIdx) {

                CellView cellView = this.cellViews.get(rowIdx).get(colIdx);
                rowAscii = rowAscii.concat(cellView.asciiDraw());
            }
            System.out.println(rowAscii);
        }
        System.out.println(rowSeparatorLine);
    }


    public void addCellViewIn(CellView cellView, int row, int column) {
        this.cellViews.get(row).put(column, cellView);
    }

    int getRowsNumber() {
        return this.gameBoard.getHeigth();
    }

    int getColumnsNumber() {
        return this.gameBoard.getWidth();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }


    public void upgradedUpdate() {
/*
        for (int rowIdx = 0; rowIdx < this.getRowsNumber(); ++rowIdx) {
            this.drawRowSeparator(rowIdx);

            String rowAscii = "";
            for (int colIdx = 0; colIdx < this.getColumnsNumber(); ++colIdx) {

                CellView cellView = this.cellViews.get(rowIdx).get(colIdx);
                rowAscii = rowAscii.concat(cellView.asciiDraw());
                rowAscii = rowAscii.concat(horizontalLinksView.asciiDrawLinkBetween(rowIdx, colIdx, rowIdx, colIdx + 1));
            }
            System.out.println(rowAscii);
        }

    }

    private void drawRowSeparator(int rowIdx) {
        String vertex = "    ";
        for (int colIdx = 0; colIdx < this.getColumnsNumber(); ++colIdx) {
            System.out.print(this.verticalLinksView.asciiDrawLinkBetween(rowIdx, colIdx));
            System.out.print(vertex);
        }
        System.out.print("\n");
    }
*/

}
