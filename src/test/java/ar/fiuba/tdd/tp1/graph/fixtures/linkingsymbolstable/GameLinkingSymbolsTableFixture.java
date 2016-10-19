package ar.fiuba.tdd.tp1.graph.fixtures.linkingsymbolstable;


import ar.fiuba.tdd.tp1.graph.LinkingSymbolsTable;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 12/10/2016.
 */
public abstract class GameLinkingSymbolsTableFixture extends LinkingSymbolsTable {

    public GameLinkingSymbolsTableFixture() {
        this.setGameLinkingSymbolsTable();
    }

    protected abstract void setGameLinkingSymbolsTable();

    protected void addLinkingTokensArrayToSymbol(String symbol, String[] linkingTokens) {
        Set<String> linkingTokensSet = new HashSet<>();
        for (int i = 0; i < linkingTokens.length; i++) {
            linkingTokensSet.add(linkingTokens[i]);
        }
        this.setSymbolsLinkingTokens(symbol, linkingTokensSet);
    }
}
