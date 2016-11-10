package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;

import java.util.*;

/*
 * Each entry of this table is structured like this:
 * |linkingSymbol|[linkingTokens]
 *  For a given symbol this table shows which linking tokens are associated to that symbol
 */
public class LinkingSymbolsTable {

    protected Map<String, Set<String>> table;

    public LinkingSymbolsTable() {
        this.table = new HashMap<>();
    }


    public void setSymbolsLinkingTokens(String symbol, Set<String> symbolLinkingTokens) {
        this.table.put(symbol, symbolLinkingTokens);
    }


    public Set<String> getLinkingTokensFor(String symbol) {
        if (this.table.containsKey(symbol)) {
            return this.table.get(symbol);
        }
        return new HashSet<>();

    }
}





