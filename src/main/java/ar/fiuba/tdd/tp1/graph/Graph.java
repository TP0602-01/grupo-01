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

    public Graph(Cell... cells) {
        this.links = new HashMap<>();
        for (Cell cell : cells) {
            addCell(cell);
        }
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

    public Collection<Cell> getLinks(Cell cell) {
        return this.links.get(cell);
    }


    public boolean cellHasConnection(Cell cell) {
        Collection<Cell> links = getLinks(cell);
        return links != null;
    }

    private int check(Vector<Cell> cells) {
        Collection<Cell> cellLinks = getLinks(cells.lastElement());

        if (cellLinks != null) {
            if (cellLinks.size() > 1) {
                System.out.println("Cell have two conextion");
            }
            for (Cell cell : cellLinks) {
                if (cells.contains(cell)) {
                    return 1;
                } else {
                    cells.add(cell);
                    check(cells);
                    return check(cells);
                }
            }
        }
        System.out.println("llego al final, algo anda mal");
        return 0;
    }

    public boolean getCircuitCount() {  //TODO: CAMBIEMOS EL NOMBRE ESTE QUE ES OTRA COSA
        Collection<Cell> cells = links.keySet();
        for (Cell cell : cells) {
            Vector<Cell> circuit = new Vector<>();
            circuit.add(cell);
            if (check(circuit) == 1) {
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


    public boolean contains(Cell cell) {
        Collection<Cell> cells = getCells();
        for (Cell cellAux : cells) {
            if (cellAux == cell) {
                return true;
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

    private static Graph singleton = null;

    public static Graph getSingleInstance() {
        if (singleton == null) {
            singleton = new Graph();
        }
        return singleton;
    }


    public int getConnectedSubGraphsCount() {
        return this.getAllConnectedSubGraphs().size();
    }

    private Collection<Set<Cell>> getAllConnectedSubGraphs() {
        Collection<Cell> cells = this.getCells();
        Collection<Set<Cell>> connectedSupGraphs = new LinkedList<>();

        for (Cell cell : cells) {
            if (!this.cellBelongsToASubgraph(connectedSupGraphs, cell)) {
                ArrayList<Cell> subGraph = new ArrayList<>();
                subGraph.add(cell);
                for (int i = 0; i < subGraph.size(); i++) {
                    Cell currentCell = subGraph.get(i);
                    for (Cell adyacentCell : this.getAdyacentCellsOf(currentCell)) {
                        if (!subGraph.contains(adyacentCell)) {
                            subGraph.add(adyacentCell);
                        }
                    }
                }
                connectedSupGraphs.add(new HashSet<>(subGraph));
            }
        }
        return connectedSupGraphs;

    }

    private boolean cellBelongsToASubgraph(Collection<Set<Cell>> connectedSupGraphs, Cell cell) {
        for (Set<Cell> subgraph : connectedSupGraphs) {
            if (subgraph.contains(cell)) {
                return true;
            }
        }
        return false;
    }

    private Set<Cell> getAdyacentCellsOf(Cell cell) {
        if (this.links.containsKey(cell)) {
            return this.links.get(cell);
        }
        return new HashSet<>();

    }

    public int getLooplessCircuitCount() {
        int circuitsCount = 0;
        Collection<Set<Cell>> connectedSubgraphs = this.getAllConnectedSubGraphs();
        /*For each subgraph each of their cells are checked, if they all have an even number of links
         then it is a circuit (Euler). In particular if all the connected subgraph's cells have 2 links, they form
          a loopless circuit
          */
        for (Set<Cell> subgraph : connectedSubgraphs) {
            boolean isCircuit = true;
            for (Cell cell : subgraph) {
                isCircuit = isCircuit && (this.getAdyacentCellsCountOf(cell) == 2);
            }
            if (isCircuit) {
                circuitsCount++;
            }
        }
        return circuitsCount;
    }

    private int getAdyacentCellsCountOf(Cell cell) {
        return this.getAdyacentCellsOf(cell).size();
    }

    public static void reset() {
        singleton = new Graph();
    }

    public static void setSingleInstance(Graph graph) {
        singleton = graph;
    }


    public int getNotDirectedLinksCount() {

        int notDirectedLinksCount = 0;
        ArrayList<Cell> cellsArray = new ArrayList<>(this.getCells());

        for (int i = 0; i < cellsArray.size(); i++) {
            Cell originCell = cellsArray.get(i);
            for (int j = i; j < cellsArray.size(); j++) {
                Cell destinationCell = cellsArray.get(j);
                if (this.notDirectedLinkExistsBetween(originCell, destinationCell)) {
                    notDirectedLinksCount++;
                }
            }
        }

        return notDirectedLinksCount;
    }

    public boolean notDirectedLinkExistsBetween(Cell firstCell, Cell secondCell) {
        return (this.linkExistsFromOriginToDestination(firstCell, secondCell)
                && this.linkExistsFromOriginToDestination(secondCell, firstCell));
    }
}





