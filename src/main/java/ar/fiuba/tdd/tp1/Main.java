package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.cell.DataCell;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.DataCellView;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is just a tp1 project");
        BoardView board = new BoardView();
        DataCell dataCell1 = new DataCell();
        DataCell dataCell2 = new DataCell();
        DataCell dataCell3 = new DataCell();

        DataCellView dataCellView1 = new DataCellView(dataCell1);
        DataCellView dataCellView2 = new DataCellView(dataCell2);
        DataCellView dataCellView3 = new DataCellView(dataCell3);

        board.addCellView(dataCellView1);
        board.addCellView(dataCellView2);
        board.addCellView(dataCellView3);

        dataCell1.setData("8");
        dataCell2.setData("5");
        board.update();

        System.out.println("dataCell3 is updated");
        dataCell3.setData("2");
        board.update();
    }

}
