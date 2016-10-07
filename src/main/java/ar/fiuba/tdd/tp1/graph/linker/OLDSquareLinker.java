package ar.fiuba.tdd.tp1.graph.linker;

import ar.fiuba.tdd.tp1.graph.linkeable.OLDLinkeableSquare;

import java.util.Set;

/**
 * Created by juanma on 06/10/16.
 */
public interface OLDSquareLinker {

    public void updateLinkeablesLinks(OLDLinkeableSquare initialLinkeable);

    //TODO se podria manejar con subindices para evitar tener tantos metodos
    public void setRightLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setUpperRightLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setUpLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setUpperLeftLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setLeftLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setLowerLeftLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setDownLinkingInfo(String originTokens, Set<String> destinationTokens);
    public void setLowerRightLinkingInfo(String originTokens, Set<String> destinationTokens);

}
