package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;

import java.util.*;

/**
 * Created by User on 12/10/2016.
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





