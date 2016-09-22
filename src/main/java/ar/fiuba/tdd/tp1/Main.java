package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.DataCellView;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a tp1 project");
        BoardView boardView = new BoardView();
        InputCell dataCell1 = new InputCell();
        InputCell dataCell2 = new InputCell();
        InputCell dataCell3 = new InputCell();

        DataCellView dataCellView1 = new DataCellView(dataCell1);
        DataCellView dataCellView2 = new DataCellView(dataCell2);
        DataCellView dataCellView3 = new DataCellView(dataCell3);

        boardView.addCellView(dataCellView1);
        boardView.addCellView(dataCellView2);
        boardView.addCellView(dataCellView3);

        dataCell1.setData(8);
        dataCell2.setData(5);
        boardView.update();

        System.out.println("dataCell3 is updated");
        dataCell3.setData(2);
        boardView.update();
    }

}
