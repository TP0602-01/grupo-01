package ar.fiuba.tdd.tp1.model.cell;

import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CellTest {

    @Test
    public void cellGetLinkingSymbols() {
        InputCell inputCell = new InputCell();
        inputCell.setData("1");

        assertEquals(inputCell.getData(), inputCell.getLinkingSymbol());
    }

    @Test
    public void cellGetLinkingTokens() {
        InputCell inputCell = new InputCell();
        Set<String> set = new HashSet<>();
        set.add("l1");
        set.add("l2");
        inputCell.setLinkingTokens(set);

        assertEquals(inputCell.getLinkingTokens(), set);
    }

    @Test
    public void cellGetLinkingTokensAddingOneByOne() {
        InputCell inputCell = new InputCell();
        inputCell.addLinkingToken("l1");
        Set<String> set = inputCell.getLinkingTokens();

        assertTrue(set.contains("l1"));
    }

    @Test
    public void getDataAsInteger() {
        InputCell inputCell = new InputCell("1");

        assertEquals(inputCell.getDataAsInteger(), 1);
    }

    @Test
    public void getDataAsIntegerThatIsNotIntegerReturns0() {
        InputCell inputCell = new InputCell("no number");

        assertEquals(inputCell.getDataAsInteger(), 0);
    }

    @Test
    public void getRowIndex() {
        InputCell inputCell = new InputCell("1");
        inputCell.setPosition(2, 1);

        assertEquals(inputCell.getRowIndex(), 2);
    }


    @Test
    public void getColumnIndex() {
        InputCell inputCell = new InputCell("1");
        inputCell.setPosition(1, 1);

        assertEquals(inputCell.getColumnIndex(), 1);
    }

    @Test
    public void addAndGetSets() {
        InputCell inputCell = new InputCell("1");
        Graph graph = new Graph();
        inputCell.addSet(graph);
        Collection<Graph> collection = inputCell.getSets();

        assertTrue(collection.contains(graph));
    }

}
