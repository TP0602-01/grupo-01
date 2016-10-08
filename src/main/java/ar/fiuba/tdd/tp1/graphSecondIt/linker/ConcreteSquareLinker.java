package ar.fiuba.tdd.tp1.graphSecondIt.linker;

import ar.fiuba.tdd.tp1.graphSecondIt.linkeable.Linkeable;
import ar.fiuba.tdd.tp1.graphSecondIt.linkeable.LinkeableSquare;
import ar.fiuba.tdd.tp1.graphSecondIt.linksManager.LinksManager;
import ar.fiuba.tdd.tp1.graphSecondIt.linksManager.MapLinkManager;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public class ConcreteSquareLinker implements SquareLinker {

    //TODO: se podrian crear objetos para que esto quede mas entendible
    //TODO: en definitiva es MAP<key=[rowOffset, colOffset], value=[ TOKEN_ORIGEN, [TOKEN_DESTINO1,..., TOKEN_DESTINOn] ]>
    Map<Pair<Integer,Integer>, Pair<String, Set<String>> > linkingInfo;

    LinkeableMatrix linkeableMatrix;
    LinksManager linksManager;




    public ConcreteSquareLinker(LinkeableMatrix linkeableMatrix, LinksManager linksManager){
        this.linkingInfo = new HashMap<>();
        this.linkeableMatrix = linkeableMatrix;
        this.linksManager = linksManager;
    }


    @Override
    public void setLinkingInfo(int rowOffset, int columnOffset, String originTokens, Set<String> destinationTokens) {
        //Setea en que direccion (representados como offsets) se van a chequear los linkeos
        Pair<Integer, Integer> directionOffset = new Pair<>(rowOffset,columnOffset);
        Pair<String, Set<String>> rightLinkingInfo = new Pair<>(originTokens, destinationTokens);
        this.linkingInfo.put(directionOffset, rightLinkingInfo );
    }

    @Override
    public void updateLinkeablesLinks(int row, int column) {

        LinkeableSquare originSquare = this.linkeableMatrix.getLinkeable(row, column);

        //Recorro cada posible offset de la tabla

        //TODO: podria hacerse mas abstracto
        for (Map.Entry<Pair<Integer,Integer>, Pair<String, Set<String>>> currentInfoEntry : this.linkingInfo.entrySet()){
            //currentInfoEntry tiene key=[rowOffset, colOffset], value=[ TOKEN_ORIGEN, [TOKEN_DESTINO1,..., TOKEN_DESTINOn] ]

            int rowOffset = currentInfoEntry.getKey().getKey();//RIP DEMETER LAW
            int columnOffset = currentInfoEntry.getKey().getValue();//RIP DEMETER LAW
            LinkeableSquare destinationSquare = this.linkeableMatrix.getLinkeable(row + rowOffset, column + columnOffset);


            if (destinationSquare != null) {
                boolean shouldBeLinked = false;


                //Tengo que acceder a la tabla de linkeo.
                String originTokenInCurrentOffset = currentInfoEntry.getValue().getKey();

                Set<String> originLinkingTokens = originSquare.getLinkingTokens();
                for (String originToken: originLinkingTokens){

                    if ( originToken.equals( originTokenInCurrentOffset ) ){

                        Set<String> compatibleTokensInCurrentOffset = currentInfoEntry.getValue().getValue();
                        Set<String> adyacentLinkingTokens = destinationSquare.getLinkingTokens();

                        for (String adyacentToken: adyacentLinkingTokens){
                            if (compatibleTokensInCurrentOffset.contains(adyacentToken)){
                                shouldBeLinked = true;
                            }
                        }
                    }
                }



                if (shouldBeLinked){
                    this.linksManager.addNotDirectedLinkBetween(originSquare, destinationSquare);
                }
                else{
                    this.linksManager.removeNotDirectedLinkBetween(originSquare, destinationSquare);
                }
            }
        }
    }
}



