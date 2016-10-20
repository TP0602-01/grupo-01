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

    public Collection<Cell> getLinks(Cell cell){
        return this.links.get(cell);
    }


    public boolean cellHasConnection(Cell cell){
        Collection<Cell> links = getLinks(cell);
        return links != null;
    }

    private int check(Vector<Cell> cells){
        Collection<Cell> cellLinks = getLinks(cells.lastElement());
        if (cellLinks != null){
            if(cellLinks.size()> 1){
                System.out.println("Cell have two conextion");
            }
        }
        if(cellLinks != null){
            for (Cell cell: cellLinks){
                if (cells.contains(cell)){
                    return 1;
                }else{
                    cells.add(cell);
                    check(cells);
                    return check(cells);
                }

            }
        }else{
            return 0;
        }
        System.out.println("llego al final, algo anda mal");
        return 0;
    }

    public boolean getCircuitCount() {
        Collection<Cell> cells = links.keySet();
        for (Cell cell: cells){
            Vector<Cell> circuit = new Vector<>();
            circuit.add(cell);
            if(check(circuit) == 1){
                return true;
            }
        }
        return false;
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
            Cell cell = (Cell) ((Map.Entry) it.next()).getKey();
            cells.add(cell);
        }
        return cells;
    }

    /* Add a cell as a node in the Graph and return true if
     * it has been addedd succesful */
    public boolean addCell(Cell cell) {
        Set<Cell> nodeLinks = this.links.get(cell);
        if (nodeLinks == null) {
            nodeLinks = new HashSet<>();
            this.links.put(cell, nodeLinks);
            cell.addSet(this);
            return true;
        }
        return false;
    }
}





