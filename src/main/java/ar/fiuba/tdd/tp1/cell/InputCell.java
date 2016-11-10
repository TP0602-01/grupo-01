package ar.fiuba.tdd.tp1.cell;

/*
 * Input cell is a cell that can change its content
 * and with 0 content represent an empty state.
 *
 */
public class InputCell extends Cell {

    public InputCell(String data) {
        super(data);
    }

    public InputCell() {
        super("0");
    }

    public boolean isEmpty() {
        return (data.equals("0"));
    }

    public void setData(String data) {
        this.data = data;
    }
}
