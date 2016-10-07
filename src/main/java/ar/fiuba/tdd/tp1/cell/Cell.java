package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.graphSecondIt.linkeable.LinkeableSquare;
import ar.fiuba.tdd.tp1.utilities.Observable;

import java.util.HashSet;
import java.util.Set;

/*  */
public abstract class Cell extends Observable implements LinkeableSquare{

    public static final String DATA_TYPE = "data";
    public static final String NULL_TYPE = "nullcell";
    public static final String KAKORU_TYPE = "kakoru";
    public static final String HINT_TYPE = "hint";


    protected Set<String> linkingTokens;


    protected String data;

    protected Cell(String data) {
        this.data = data;
        this.linkingTokens = new HashSet<>();
    }


    public abstract boolean isEmpty();

    public String getData() {
        return this.data;
    }





    private LinkeableSquare rightLinked;
    private LinkeableSquare leftLinked;

    @Override
    public LinkeableSquare getRightLinked() {
        return this.rightLinked;
    }

    @Override
    public LinkeableSquare getLeftLinked() {
        return this.leftLinked;
    }

    @Override
    public void setRightLinked(LinkeableSquare linked) {
        this.rightLinked = linked;
    }

    @Override
    public void setLeftLinked(LinkeableSquare linked) {
        this.leftLinked = linked;
    }


    @Override
    public Set<String> getLinkingTokens() {
        return this.linkingTokens;
    }

    @Override
    public void setLinkingTokens(Set<String> linkingTokens) {
        this.linkingTokens = linkingTokens;
    }










    // TODO: borrarlo despues
    public void setData(String data) {}

    public abstract boolean isWalkable();

}
