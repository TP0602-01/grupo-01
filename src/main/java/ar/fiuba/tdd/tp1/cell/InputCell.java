package ar.fiuba.tdd.tp1.cell;

/* */
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

    @Override
    public boolean isWalkable() {
        return true;
    }

    public void setData(String data) {
        this.data = data;
    }


}
