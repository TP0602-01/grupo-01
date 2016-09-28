package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.utilities.Observable;
import ar.fiuba.tdd.tp1.utilities.Observer;

import java.util.ArrayList;
import java.util.Collection;

public class BoardView extends Observer {

    //TODO It might be a map to identify column and row
    Collection<CellView> cellViews;
    GameBoard board;

    public BoardView(GameBoard board) {
        this.board = board;
        cellViews = new ArrayList<>();
    }

    @Override
    public void update() {
        for (CellView cellView : this.cellViews) {
            System.out.println("#####");
            cellView.draw();
            System.out.println("#####");
        }
        board.getCell(1,1);
    }

    //TODO It might need column and row as parameters
    public void addCellView(CellView cellView) {
        this.cellViews.add(cellView);
    }
}
