package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by User on 17/10/2016.
 */
public class LinkingTableTests {

    @Test
    public void testAddingAnEntryToALinkingTableAndCheckingThatEntryExistanceMustBeTrue() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "ConfigurableOriginToken";
        String destinationToken = "ConfigurableDestinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertTrue(linkingTable.checkEntryExistance(rowOffset, colOffset, originToken, destinationToken));
    }

    @Test
    public void testCheckingEntryExistanceOfAnNonExistingEntryMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, "NonExistingOriginToken", "NonExistingDestinationToken"));
    }

    @Test
    public void testCheckingEntryExistanceOfAnEntryWithAnNonExistingOriginTokenMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, "NonExistingOriginToken", destinationToken));
    }

    @Test
    public void testCheckingEntryExistanceOfAnEntryWithAnNonExistingDestinationTokenMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, originToken, "NonExistingDestinationToken"));
    }


}
