package ar.fiuba.tdd.tp1.cell;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.utilities.Observable;

import java.util.HashSet;
import java.util.Set;

/*  */
public abstract class Cell extends Observable implements Linkable {

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

    public int getDataAsInteger(){
        try {
            return Integer.parseInt(this.getData());
        }
        catch (NumberFormatException e){
            return 0;
        }

    }


    @Override
    public String getLinkingSymbol(){
        return this.getData();
    }

    @Override
    public Set<String> getLinkingTokens() {
        return this.linkingTokens;
    }

    @Override
    public void setLinkingTokens(Set<String> linkingTokens) {
        this.linkingTokens = linkingTokens;
    }

    @Override
    public void addLinkingToken(String token) {
        this.linkingTokens.add(token);
    }


    // TODO: borrarlo despues
    public void setData(String data) {
    }

    public abstract boolean isWalkable();

}
