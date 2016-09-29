package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;

/**
 * Created by juanma on 28/09/16.
 */

public class KakoruCellView implements CellView {


    private Integer rightUpSum;
    private Integer leftDownSum;

    public KakoruCellView(Integer rightUpSum, Integer leftDownSum) {
        this.rightUpSum = rightUpSum;
        this.leftDownSum = leftDownSum;
    }


    public KakoruCellView(Cell cell){
        String cellContent = cell.getData(); //"10/3"
        this.rightUpSum = Integer.parseInt(cellContent.split("/")[0]);
        this.leftDownSum = Integer.parseInt(cellContent.split("/")[1]);
    }


    @Override
    public String ASCIIdraw() {

        String ASCIIcell = "";

        if (this.leftDownSum == null){
            ASCIIcell = ASCIIcell.concat("|##\\");
        }
        else if (this.leftDownSum < 10 ){
            ASCIIcell = ASCIIcell.concat("|" + this.leftDownSum + " \\");
        }
        else {
            ASCIIcell = ASCIIcell.concat("|" + this.leftDownSum + "\\");
        }

        if (this.rightUpSum == null){
            ASCIIcell = ASCIIcell.concat("##|");
        }
        else if (this.rightUpSum < 10 ) {
            ASCIIcell = ASCIIcell.concat(this.rightUpSum + " |");
        }
        else {
            ASCIIcell = ASCIIcell.concat(this.rightUpSum + "|");
        }

        return ASCIIcell;
    }
}
