package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.OLDLinkeableSquare;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by juanma on 06/10/16.
 */
public class OLDConcreteSquareLinker implements OLDSquareLinker {

    //TODO: migth be a Map<String, Map<String, Set<String>> >
    Map<String, Pair<String, Set<String>> > linkingInfo;

    public static final String RIGHT = "RIGHT";
    public static final String LEFT = "LEFT";


    public OLDConcreteSquareLinker(){
        this.linkingInfo = new HashMap<>();

        //TODO: se podrian usar en la configuracion indices relativos en vez de direcciones hardcodeadas
        //EJEMPLO: En el archivo de confg "RIGHT" seria col + 1. "UPPER_RIGHT" seria col + 1, row - 1, etc

        this.linkingInfo.put(this.RIGHT, null);
        //this.linkingInfo.put("UPPER_RIGHT", null);
        //this.linkingInfo.put("UP", null);
        //this.linkingInfo.put("UPPER_LEFT", null);
        this.linkingInfo.put(this.LEFT, null);
        /*
        this.linkingInfo.put("LOWER_LEFT", null);
        this.linkingInfo.put("DOWN", null);
        this.linkingInfo.put("LOWER_RIGHT", null);
        */
    }

    @Override
    public void updateLinkeablesLinks(OLDLinkeableSquare originLinkeable) {

        //this.checkLinkingTokens(originLinkeable, originLinkeable.getRightNeighbour(), this.RIGHT);
        //this.checkLinkingTokens(originLinkeable, originLinkeable.getLeftNeighbour(), this.LEFT );
        //TODO: pensar una mejor forma de chequear esto o agregar los otros 6 metodos pare el resto de las direcciones...
    }


    private void checkLinkingTokens(OLDLinkeableSquare originSquare, OLDLinkeableSquare adyacentSquare, String direction){
        if (adyacentSquare != null) {
            boolean shouldBeLinked = false;
            Set<String> originLinkingTokens = originSquare.getLinkingTokens();

            //Tengo que acceder a la tabla de linkeo.
            String originTokenInChosenDirection = this.linkingInfo.get(direction).getKey();
            for (String originToken: originLinkingTokens){
                if ( originToken.equals( originTokenInChosenDirection ) ){
                    Set<String> compatibleTokensInChoseDirection = this.linkingInfo.get(direction).getValue();
                    Set<String> adyacentLinkingTokens = adyacentSquare.getLinkingTokens();

                    for (String adyacentToken: adyacentLinkingTokens){
                        if (compatibleTokensInChoseDirection.contains(adyacentToken)){
                            shouldBeLinked = true;
                        }
                    }
                }
            }


            if (shouldBeLinked){
                //TODO: esto va a quedar horrible con los otros 6 metodos, tal vez se podria hacer algo con el GameBoard
                if (direction.equals(this.RIGHT)){
                    originSquare.setRightLinked(adyacentSquare);
                }
                if (direction.equals(this.LEFT)){
                    originSquare.setLeftLinked(adyacentSquare);
                }
            }

        }
    }


    @Override
    public void setRightLinkingInfo(String originTokens, Set<String> destinationTokens) {
        //Warning: se agrega el Set, NO se copia, si el set se modica afuera, se modifica aca
        Pair<String, Set<String>> rightLinkingInfo = new Pair<>(originTokens, destinationTokens);
        this.linkingInfo.put(this.RIGHT, rightLinkingInfo );
    }

    @Override
    public void setLeftLinkingInfo(String originTokens, Set<String> destinationTokens) {
        Pair<String, Set<String>> leftLinkingInfo = new Pair<>(originTokens, destinationTokens);
        this.linkingInfo.put(this.LEFT, leftLinkingInfo );
    }


    //TODO: para este proto-prototipo solo voy a usar Right y Left
    @Override
    public void setUpperRightLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }

    @Override
    public void setUpLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }

    @Override
    public void setUpperLeftLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }

    @Override
    public void setLowerLeftLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }

    @Override
    public void setDownLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }

    @Override
    public void setLowerRightLinkingInfo(String originTokens, Set<String> destinationTokens) {

    }
}
