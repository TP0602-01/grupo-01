package ar.fiuba.tdd.tp1.graph.linkeable;


import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by juanma on 06/10/16.
 */
public class OLDLinkedCell implements OLDLinkeableSquare {



    private OLDLinkeableSquare rightLinked;
    private OLDLinkeableSquare leftLinked;

    private OLDLinkeableSquare upLink;
    private OLDLinkeableSquare downLink;

    private OLDLinkeableSquare upperLeftLink;
    private OLDLinkeableSquare upperRightLink;

    private OLDLinkeableSquare lowerLeftLink;
    private OLDLinkeableSquare lowerRightLink;

    protected Set<String> linkingTokens;

    /*
    private OLDLinkeableSquare rightNeighbour;
    private OLDLinkeableSquare leftNeighbour;
    */


    public OLDLinkedCell(String data) {
        this.linkingTokens = new HashSet<>();
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
    public OLDLinkeableSquare getRightLinked() {
        return this.rightLinked;
    }

    @Override
    public OLDLinkeableSquare getLeftLinked() {
        return this.leftLinked;
    }

    @Override
    public void setRightLinked(OLDLinkeableSquare link) {
        this.rightLinked = link;
    }

    @Override
    public void setLeftLinked(OLDLinkeableSquare link) {
        this.leftLinked = link;
    }


    /*
    @Override
    public OLDLinkeableSquare getRightNeighbour() {
        return this.rightNeighbour;
    }

    @Override
    public OLDLinkeableSquare getLeftNeighbour() {
        return this.leftNeighbour;
    }

    @Override
    public void setRightNeighbour(OLDLinkeableSquare rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    @Override
    public void setLeftNeighbour(OLDLinkeableSquare leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }
    */





















    @Override
    public OLDLinkeableSquare getUpLink() {
        return null;
    }

    @Override
    public OLDLinkeableSquare getDownLink() {
        return null;
    }

    @Override
    public OLDLinkeableSquare getUpperLeftLink() {
        return null;
    }

    @Override
    public OLDLinkeableSquare getUpperRightLink() {
        return null;
    }

    @Override
    public OLDLinkeableSquare getLowerLeftLink() {
        return null;
    }

    @Override
    public OLDLinkeableSquare getLowerRightLink() {
        return null;
    }






    @Override
    public void setUpLink(OLDLinkeableSquare link) {

    }

    @Override
    public void setDownLink(OLDLinkeableSquare link) {

    }

    @Override
    public void setUpperLeftLink(OLDLinkeableSquare link) {

    }

    @Override
    public void setUpperRightLink(OLDLinkeableSquare link) {

    }

    @Override
    public void setLowerLeftLink(OLDLinkeableSquare link) {

    }

    @Override
    public void setLowerRightLink(OLDLinkeableSquare link) {

    }

}
