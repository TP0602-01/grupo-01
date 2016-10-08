package ar.fiuba.tdd.tp1.graphSecondIt.linkeable;

/**
 * Created by juanma on 07/10/16.
 */
public interface LinkeableSquare extends Linkeable{
    //MIENTRAS NO EXISTA LA ENTIDAD QUE MANEJE ARISTAS ESTO TIENE QUE ESTAR ASI
    //CUANDO EXISTA ESA ENTIDAD SE PODRIA USAR SOLAMENTE LA INTERFAZ "Linkeable" O CAMBIARLE EL NOMBRE A "Node"

    public LinkeableSquare getRightLinked();
    public LinkeableSquare getLeftLinked();

    public void setRightLinked(LinkeableSquare righ);
    public void setLeftLinked(LinkeableSquare link);

}
