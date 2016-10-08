package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.utilities.Observable;

/*  */
public abstract class Cell extends Observable {

    public static final String DATA_TYPE = "data";
    public static final String NULL_TYPE = "nullcell";
    public static final String KAKORU_TYPE = "kakoru";
    public static final String HINT_TYPE = "hint";


    protected String data;

    Cell(String data) {
        this.data = data;
    }


    public abstract boolean isEmpty();

    public String getData() {
        return this.data;
    }

    // TODO: borrarlo despues
    public void setData(String data) {
    }

    public abstract boolean isWalkable();

}
