package ar.fiuba.tdd.tp1.graph.linkeable;

import java.util.Set;

/* */
public interface Linkable {

    Set<String> getLinkingTokens();

    void setLinkingTokens(Set<String> linkingTokens);

    void addLinkingToken(String token);
}
