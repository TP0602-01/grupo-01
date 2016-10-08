package ar.fiuba.tdd.tp1.graph.linksManager;

import ar.fiuba.tdd.tp1.graph.linkeable.Linkeable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by juanma on 07/10/16.
 */
public class MapLinkManager implements  LinksManager{

    Map<Linkeable, Set<Linkeable>> links;

    public MapLinkManager(){
        this.links = new HashMap<>();
    }

    @Override
    public void addDirectedLinkBetween(Linkeable origin, Linkeable destination) {
        Set<Linkeable> originsLinks = this.links.get(origin);
        if (originsLinks == null){
            originsLinks = new HashSet<>();
        }
        originsLinks.add(destination);
        this.links.put(origin, originsLinks);

    }

    @Override
    public void addNotDirectedLinkBetween(Linkeable first, Linkeable second) {
        this.addDirectedLinkBetween(first, second);
        this.addDirectedLinkBetween(second, first);
    }


    @Override
    public void removeDirectedLinkBetween(Linkeable origin, Linkeable destination) {
        Set<Linkeable> originsLinks = this.links.get(origin);
        if (originsLinks != null){
            if(originsLinks.contains(destination) ) {
                originsLinks.remove(destination);
            }
        }
    }

    @Override
    public void removeNotDirectedLinkBetween(Linkeable first, Linkeable second) {
        this.removeDirectedLinkBetween(first, second);
        this.removeDirectedLinkBetween(second, first);
    }

    @Override
    public boolean linkExistsFromOriginToDestination(Linkeable origin, Linkeable destination) {

        if ( this.links.containsKey(origin) ){
           Set<Linkeable> originsLinks = this.links.get(origin);
           if (originsLinks != null){
               return originsLinks.contains(destination);
           }
        }
        return false;


    }
}
