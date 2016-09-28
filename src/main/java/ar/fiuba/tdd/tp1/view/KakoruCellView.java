package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.InputCell;

/**
 * Created by juanma on 28/09/16.
 */

//TODO: name might change
public class KakoruCellView implements CellView {

    private Integer rightUpSum;
    private Integer leftDownSum;

    public KakoruCellView(Integer rightUpSum, Integer leftDownSum) {
        this.rightUpSum = rightUpSum;
        this.leftDownSum = leftDownSum;
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