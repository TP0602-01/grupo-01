package ar.fiuba.tdd.tp1.graphSecondIt.linkeable;

import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public interface Linkeable {
    public Set<String> getLinkingTokens();
    public void setLinkingTokens(Set<String> linkingTokens);
}
