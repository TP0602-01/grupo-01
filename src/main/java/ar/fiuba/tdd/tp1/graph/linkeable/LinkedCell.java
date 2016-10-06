package ar.fiuba.tdd.tp1.graph.linkeable;


import java.util.HashSet;
import java.util.Set;

/**
 * Created by juanma on 06/10/16.
 */
public class LinkedCell implements LinkeableSquare{



    private LinkeableSquare rightLinked;
    private LinkeableSquare leftLinked;

    private LinkeableSquare upLink;
    private LinkeableSquare downLink;

    private LinkeableSquare upperLeftLink;
    private LinkeableSquare upperRightLink;

    private LinkeableSquare lowerLeftLink;
    private LinkeableSquare lowerRightLink;

    protected Set<String> linkingTokens;

    private LinkeableSquare rightNeighbour;
    private LinkeableSquare leftNeighbour;

    public LinkedCell(){
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
    public LinkeableSquare getRightLinked() {
        return this.rightLinked;
    }

    @Override
    public LinkeableSquare getLeftLinked() {
        return this.leftLinked;
    }

    @Override
    public void setRightLinked(LinkeableSquare link) {
        this.rightLinked = link;
    }

    @Override
    public void setLeftLinked(LinkeableSquare link) {
        this.leftLinked = link;
    }

    @Override
    public LinkeableSquare getRightNeighbour() {
        return this.rightNeighbour;
    }

    @Override
    public LinkeableSquare getLeftNeighbour() {
        return this.leftNeighbour;
    }

    @Override
    public void setRightNeighbour(LinkeableSquare rightNeighbour) {
        this.rightNeighbour = rightNeighbour;
    }

    @Override
    public void setLeftNeighbour(LinkeableSquare leftNeighbour) {
        this.leftNeighbour = leftNeighbour;
    }






















    @Override
    public LinkeableSquare getUpLink() {
        return null;
    }

    @Override
    public LinkeableSquare getDownLink() {
        return null;
    }

    @Override
    public LinkeableSquare getUpperLeftLink() {
        return null;
    }

    @Override
    public LinkeableSquare getUpperRightLink() {
        return null;
    }

    @Override
    public LinkeableSquare getLowerLeftLink() {
        return null;
    }

    @Override
    public LinkeableSquare getLowerRightLink() {
        return null;
    }






    @Override
    public void setUpLink(LinkeableSquare link) {

    }

    @Override
    public void setDownLink(LinkeableSquare link) {

    }

    @Override
    public void setUpperLeftLink(LinkeableSquare link) {

    }

    @Override
    public void setUpperRightLink(LinkeableSquare link) {

    }

    @Override
    public void setLowerLeftLink(LinkeableSquare link) {

    }

    @Override
    public void setLowerRightLink(LinkeableSquare link) {

    }
}
