package ar.fiuba.tdd.tp1.graph.linkeable;


/**
 * Created by juanma on 06/10/16.
 */
public interface OLDLinkeableSquare extends OLDLinkeable {

    //Tal vez se podria hacer una especie de configuracion del board

    //Suena mal, es solo para ejemplificar
    public OLDLinkeableSquare getRightLinked();
    public OLDLinkeableSquare getLeftLinked();


    /*
    //Un Neighbour es alguien que la celda tiene al lado, que esta tocando a la celda
    //Un Linked es alguien al que se puede llegar desde la celda, que tiene una arista con esta celda
    public OLDLinkeableSquare getRightNeighbour();
    public OLDLinkeableSquare getLeftNeighbour();

    public void setRightNeighbour(OLDLinkeableSquare rightNeighbour);
    public void setLeftNeighbour(OLDLinkeableSquare leftNeighbour);
    */
    /*TODO: encontrar una mejor forma de setear estas cosas o agregar los otros 12 metodos...
            en total esta interfaz tendria 32 metodos, 16 gets y 16 sets.....
    */




    public OLDLinkeableSquare getUpLink();
    public OLDLinkeableSquare getDownLink();

    public OLDLinkeableSquare getUpperLeftLink();
    public OLDLinkeableSquare getUpperRightLink();

    public OLDLinkeableSquare getLowerLeftLink();
    public OLDLinkeableSquare getLowerRightLink();

    public void setRightLinked(OLDLinkeableSquare link);
    public void setLeftLinked(OLDLinkeableSquare link);

    public void setUpLink(OLDLinkeableSquare link);
    public void setDownLink(OLDLinkeableSquare link);

    public void setUpperLeftLink(OLDLinkeableSquare link);
    public void setUpperRightLink(OLDLinkeableSquare link);

    public void setLowerLeftLink(OLDLinkeableSquare link);
    public void setLowerRightLink(OLDLinkeableSquare link);

}
