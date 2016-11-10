package ar.fiuba.tdd.tp1.graph.fixtures.linkingsymbolstable;


public class CountryRoadLinkingSymbolsFixture extends GameLinkingSymbolsTableFixture {

    public CountryRoadLinkingSymbolsFixture() {
        super();
    }

    @Override
    protected void setGameLinkingSymbolsTable() {
        this.addLinkingTokensArrayToSymbol("¡-", new String[] {"R", "D"});
        this.addLinkingTokensArrayToSymbol("!-", new String[] {"R", "U"});
        this.addLinkingTokensArrayToSymbol("-!", new String[] {"L", "U"});
        this.addLinkingTokensArrayToSymbol("-¡", new String[] {"L", "D"});
        this.addLinkingTokensArrayToSymbol("--", new String[] {"L", "R"});
        this.addLinkingTokensArrayToSymbol("|", new String[] {"U", "D"});
    }
}
