package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.DataCell;

/**
 * Created by juanma on 21/09/16.
 */
public class DataCellView implements CellView{

    private DataCell cell;

    public DataCellView(DataCell cell){
        this.cell = cell;
    }

    @Override
    public void draw() {
        System.out.println( "# " + this.cell.getData() + " #");
    }
}
