package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;

import java.util.*;

/*
 */
public class Graph {

    private Map<Cell, Set<Cell>> links;

    public Graph() {
        this.links = new HashMap<>();
    }

    /* Add Link beetween Cell origin and destination */
    public void addDirectedLinkBetween(Cell origin, Cell destination) {
        Set<Cell> originsLinks = this.links.get(origin);
        if (originsLinks == null) {
            originsLinks = new HashSet<>();
        }
        originsLinks.add(destination);
        this.links.put(origin, originsLinks);
    }

    /* Add Link beetween Cell first and second and
     * Link beetween Cell second and first */
    public void addNotDirectedLinkBetween(Cell first, Cell second) {
        this.addDirectedLinkBetween(first, second);
        this.addDirectedLinkBetween(second, first);
    }

    /* Remove Link beetween origin and dest */
    public void removeDirectedLinkBetween(Cell origin, Cell destination) {
        Set<Cell> originsLinks = this.links.get(origin);
        if (originsLinks != null) {
            if (originsLinks.contains(destination)) {
                originsLinks.remove(destination);
            }
        }
    }

    /* Remove Link beetween origin and dest and
    *  beetween dest and origin */
    public void removeNotDirectedLinkBetween(Cell first, Cell second) {
        this.removeDirectedLinkBetween(first, second);
        this.removeDirectedLinkBetween(second, first);
    }

    /* Exists link beetween origin and destination ? */
    public boolean linkExistsFromOriginToDestination(Cell origin, Cell destination) {
        if (this.links.containsKey(origin)) {
            Set<Cell> originsLinks = this.links.get(origin);
            if (originsLinks != null) {
                return originsLinks.contains(destination);
            }
        }
        return false;
    }

    /* Return a Collection with all the Cells in the Graph */
    public Collection<Cell> getCells() {
        Collection<Cell> cells = new ArrayList<>();
        Iterator it = links.entrySet().iterator();
        while (it.hasNext()) {
            Cell cell = (Cell) ((Map.Entry)it.next()).getKey();
            cells.add(cell);
        }
        return cells;
    }
}





