package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.graph.IndexedGraph;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by User on 10/11/2016.
 */
public class RuleTests {
    protected Collection<Cell> createCellsCollection(Cell[] cells) {
        Collection<Cell> cellsColection = new LinkedList<>();
        for (int i = 0; i < cells.length; i++) {
            cellsColection.add(cells[i]);
        }
        return  cellsColection;
    }

    protected IndexedGraph createIndexedGraph(Cell[] cells, Graph graph ) {
        return new IndexedGraph(this.createCellsCollection(cells), graph);
    }

    protected Queue<IndexedGraph> createIndexedGraphsQueueOfOne(Cell[] cells, Graph graph) {
        Queue<IndexedGraph> subgraphs = new LinkedList<>();
        subgraphs.add(this.createIndexedGraph(cells, graph));
        return subgraphs;
    }
}
