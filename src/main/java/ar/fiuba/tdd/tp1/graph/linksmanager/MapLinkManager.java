package ar.fiuba.tdd.tp1.graph.linksmanager;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* */
public class MapLinkManager implements LinksManager {

    private Map<Linkable, Set<Linkable>> links;

    public MapLinkManager() {
        this.links = new HashMap<>();
    }

    @Override
    public void addDirectedLinkBetween(Linkable origin, Linkable destination) {
        Set<Linkable> originsLinks = this.links.get(origin);
        if (originsLinks == null) {
            originsLinks = new HashSet<>();
        }
        originsLinks.add(destination);
        this.links.put(origin, originsLinks);
    }

    @Override
    public void addNotDirectedLinkBetween(Linkable first, Linkable second) {
        this.addDirectedLinkBetween(first, second);
        this.addDirectedLinkBetween(second, first);
    }


    @Override
    public void removeDirectedLinkBetween(Linkable origin, Linkable destination) {
        Set<Linkable> originsLinks = this.links.get(origin);
        if (originsLinks != null) {
            if (originsLinks.contains(destination)) {
                originsLinks.remove(destination);
            }
        }
    }

    @Override
    public void removeNotDirectedLinkBetween(Linkable first, Linkable second) {
        this.removeDirectedLinkBetween(first, second);
        this.removeDirectedLinkBetween(second, first);
    }

    @Override
    public boolean linkExistsFromOriginToDestination(Linkable origin, Linkable destination) {
        if (this.links.containsKey(origin)) {
            Set<Linkable> originsLinks = this.links.get(origin);
            if (originsLinks != null) {
                return originsLinks.contains(destination);
            }
        }
        return false;
    }
}
