package ar.fiuba.tdd.tp1.graph.linkeable;

import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public interface Linkeable {
    public Set<String> getLinkingTokens();
    public void setLinkingTokens(Set<String> linkingTokens);
    public void addLinkingToken(String token);
}
