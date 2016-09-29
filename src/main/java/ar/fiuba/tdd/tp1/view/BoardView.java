package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.utilities.Observer;

import java.util.*;

public class BoardView extends Observer {

    private Vector<Vector<CellView>> cellViews;
    private GameBoard gameBoard;

    public BoardView(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.gameBoard.registerObserver(this);

        cellViews = new Vector<Vector<CellView>>();
        for (int rowIdx = 0; rowIdx < this.getRowsNumber(); rowIdx++) {
            this.cellViews.insertElementAt(new Vector<CellView>(this.getColumnsNumber()), rowIdx);
        }
    }

    @Override
    public void update() {
        String rowSeparatorLine = "";
        for (int i = 0; i < this.getColumnsNumber(); i++) {
            rowSeparatorLine = rowSeparatorLine.concat("-------");
        }

        for (Vector<CellView> rowView : this.cellViews) {
            System.out.println(rowSeparatorLine);
            String rowAscii = "";
            for (CellView cellView : rowView) {
                rowAscii = rowAscii.concat(cellView.asciiDraw());
            }
            System.out.println(rowAscii);
        }
        System.out.println(rowSeparatorLine);
    }

    public void addCellViewIn(CellView cellView, int row, int column) {
        this.cellViews.elementAt(row).insertElementAt(cellView, column);
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
}
