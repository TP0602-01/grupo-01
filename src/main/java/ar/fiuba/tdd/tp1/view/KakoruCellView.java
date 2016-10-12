package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.cell.Cell;

/* */
public class KakoruCellView implements CellView {
    private Integer rightUpSum;
    private Integer leftDownSum;

    public KakoruCellView(Integer rightUpSum, Integer leftDownSum) {
        this.rightUpSum = rightUpSum;
        this.leftDownSum = leftDownSum;
    }

    public KakoruCellView(Cell cell) {
        String cellContent = cell.getData();

        String leftNumber = cellContent.split("/")[0];
        if (leftNumber.matches("_")) {
            this.leftDownSum = null;
        } else {
            this.leftDownSum = Integer.parseInt(leftNumber);
        }

        String rightNumber = cellContent.split("/")[1];
        if (rightNumber.matches("_")) {
            this.rightUpSum = null;
        } else {
            this.rightUpSum = Integer.parseInt(rightNumber);
        }
    }

    private String drawLeftDownSum(String asciiCell) {
        if (this.leftDownSum == null) {
            asciiCell = asciiCell.concat("|##\\");
        } else if (this.leftDownSum < 10) {
            asciiCell = asciiCell.concat("|" + this.leftDownSum + " \\");
        } else {
            asciiCell = asciiCell.concat("|" + this.leftDownSum + "\\");
        }
        return asciiCell;
    }

    private String drawRightUpSum(String asciiCell) {
        if (this.rightUpSum == null) {
            asciiCell = asciiCell.concat("##|");
        } else if (this.rightUpSum < 10) {
            asciiCell = asciiCell.concat(this.rightUpSum + " |");
        } else {
            asciiCell = asciiCell.concat(this.rightUpSum + "|");
        }
        return asciiCell;
    }

    @Override
    public String asciiDraw() {
        String asciiCell = "";
        asciiCell = drawLeftDownSum(asciiCell);
        asciiCell = drawRightUpSum(asciiCell);
        return asciiCell;
    }
}
