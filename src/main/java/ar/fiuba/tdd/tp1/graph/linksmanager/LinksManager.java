package ar.fiuba.tdd.tp1.graph.linksmanager;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;

/* */
public interface LinksManager {

    void addDirectedLinkBetween(Linkable origin, Linkable destination);

    void addNotDirectedLinkBetween(Linkable first, Linkable second);

    void removeDirectedLinkBetween(Linkable origin, Linkable destination);

    void removeNotDirectedLinkBetween(Linkable first, Linkable second);

    boolean linkExistsFromOriginToDestination(Linkable origin, Linkable destination);

}
