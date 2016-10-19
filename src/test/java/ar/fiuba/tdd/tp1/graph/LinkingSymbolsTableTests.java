package ar.fiuba.tdd.tp1.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by User on 12/10/2016.
 */
public class LinkingSymbolsTableTests {

    @Test
    public void testForAGivingSymbolItsLinkingTokensAreReturned() {

        Set<String> symbolLinkingTokens = new HashSet<>();
        symbolLinkingTokens.add("token1");
        symbolLinkingTokens.add("token2");
        symbolLinkingTokens.add("*****3");

        LinkingSymbolsTable symbolsTable = new LinkingSymbolsTable();
        String symbol = "***";
        symbolsTable.setSymbolsLinkingTokens(symbol, symbolLinkingTokens);
        assertTrue(symbolLinkingTokens.containsAll(symbolsTable.getLinkingTokensFor(symbol)));
    }

    @Test
    public void testForANonExistingSymbolAnEmptySetOfLinkingTokensIsReturned() {
        LinkingSymbolsTable symbolsTable = new LinkingSymbolsTable();
        assertTrue( (symbolsTable.getLinkingTokensFor("Non Existing Symbol")).isEmpty());
    }
}
