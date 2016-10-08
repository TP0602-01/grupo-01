package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkeable;
import ar.fiuba.tdd.tp1.graph.linksManager.LinksManager;
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
    Map<Pair<Integer,Integer>, Map<String, Set<String>> > linkingInfo;

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
        Map<String, Set<String>> linkeableTokensInChosenOffset;
        if ( this.linkingInfo.containsKey(directionOffset) ){
            linkeableTokensInChosenOffset = this.linkingInfo.get(directionOffset);
        }
        else {
            linkeableTokensInChosenOffset = new HashMap<>();
        }
        linkeableTokensInChosenOffset.put(originTokens, destinationTokens);
        this.linkingInfo.put( directionOffset, linkeableTokensInChosenOffset);

    }

    @Override
    public void updateLinkeablesLinks(int row, int column) {

        Linkeable origin = this.linkeableMatrix.getLinkeable(row, column);
        Set<String> originLinkingTokens = origin.getLinkingTokens();

        //Recorro cada posible offset de la tabla

        //TODO: podria hacerse mas abstracto
        for (Map.Entry<Pair<Integer,Integer>, Map<String, Set<String>>> currentInfoEntry : this.linkingInfo.entrySet()){
            //currentInfoEntry tiene key=[rowOffset, colOffset], value=[ TOKEN_ORIGEN, [TOKEN_DESTINO1,..., TOKEN_DESTINOn] ]

            int rowOffset = currentInfoEntry.getKey().getKey();//RIP DEMETER LAW
            int columnOffset = currentInfoEntry.getKey().getValue();//RIP DEMETER LAW
            Linkeable destination = this.linkeableMatrix.getLinkeable(row + rowOffset, column + columnOffset);


            if (destination != null) {
                boolean shouldBeLinked = false;


                //Tengo que acceder a la tabla de linkeo.
                Map<String, Set<String>> currentLinkeableTokensInCurrentOffset = currentInfoEntry.getValue();



                for (String originToken: originLinkingTokens){

                    if ( currentLinkeableTokensInCurrentOffset.containsKey(originToken) ){


                        Set<String> compatibleDestinationTokensInCurrentOffset = currentLinkeableTokensInCurrentOffset.get(originToken);
                        Set<String> destinationLinkingTokens = destination.getLinkingTokens();

                        for (String adyacentToken: destinationLinkingTokens){
                            if (compatibleDestinationTokensInCurrentOffset.contains(adyacentToken)){
                                shouldBeLinked = true;
                            }
                        }
                    }
                }

                if (shouldBeLinked){
                    this.linksManager.addNotDirectedLinkBetween(origin, destination);
                }
                else{
                    this.linksManager.removeNotDirectedLinkBetween(origin, destination);
                }
            }
        }
    }
}



