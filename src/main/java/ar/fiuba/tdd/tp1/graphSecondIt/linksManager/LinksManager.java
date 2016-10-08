package ar.fiuba.tdd.tp1.graphSecondIt.linksManager;

import ar.fiuba.tdd.tp1.graphSecondIt.linkeable.Linkeable;

/**
 * Created by juanma on 07/10/16.
 */
public interface LinksManager {

    public void addDirectedLinkBetween(Linkeable origin, Linkeable destination);
    public void addNotDirectedLinkBetween(Linkeable first, Linkeable second);

    public void removeDirectedLinkBetween(Linkeable origin, Linkeable destination);
    public void removeNotDirectedLinkBetween(Linkeable first, Linkeable second);

    public boolean linkExistsFromOriginToDestination(Linkeable origin, Linkeable destination);

}
