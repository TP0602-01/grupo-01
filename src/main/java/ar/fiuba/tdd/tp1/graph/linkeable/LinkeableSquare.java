package ar.fiuba.tdd.tp1.graph.linkeable;


/**
 * Created by juanma on 06/10/16.
 */
public interface LinkeableSquare extends Linkeable{

    //Tal vez se podria hacer una especie de configuracion del board

    //Suena mal, es solo para ejemplificar
    public LinkeableSquare getRightLinked();
    public LinkeableSquare getLeftLinked();

    //Un Neighbour es alguien que la celda tiene al lado, que esta tocando a la celda
    //Un Linked es alguien al que se puede llegar desde la celda, que tiene una arista con esta celda
    public LinkeableSquare getRightNeighbour();
    public LinkeableSquare getLeftNeighbour();

    public void setRightNeighbour(LinkeableSquare rightNeighbour);
    public void setLeftNeighbour(LinkeableSquare leftNeighbour);
    /*TODO: encontrar una mejor forma de setear estas cosas o agregar los otros 12 metodos...
            en total esta interfaz tendria 32 metodos, 16 gets y 16 sets.....
    */








    public LinkeableSquare getUpLink();
    public LinkeableSquare getDownLink();

    public LinkeableSquare getUpperLeftLink();
    public LinkeableSquare getUpperRightLink();

    public LinkeableSquare getLowerLeftLink();
    public LinkeableSquare getLowerRightLink();

    public void setRightLinked(LinkeableSquare link);
    public void setLeftLinked(LinkeableSquare link);

    public void setUpLink(LinkeableSquare link);
    public void setDownLink(LinkeableSquare link);

    public void setUpperLeftLink(LinkeableSquare link);
    public void setUpperRightLink(LinkeableSquare link);

    public void setLowerLeftLink(LinkeableSquare link);
    public void setLowerRightLink(LinkeableSquare link);

}
