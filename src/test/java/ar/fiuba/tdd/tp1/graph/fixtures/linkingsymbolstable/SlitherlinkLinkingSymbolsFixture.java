package ar.fiuba.tdd.tp1.graph.fixtures.linkingsymbolstable;

public class SlitherlinkLinkingSymbolsFixture extends GameLinkingSymbolsTableFixture {

    public SlitherlinkLinkingSymbolsFixture() {
        super();
    }

    @Override
    protected void setGameLinkingSymbolsTable() {

        this.addLinkingTokensArrayToSymbol(": |", new String[] {"U", "L", "D"});
        this.addLinkingTokensArrayToSymbol(":^:", new String[] {"L", "D", "R"});
        this.addLinkingTokensArrayToSymbol("| :", new String[] {"U", "D", "R"});
        this.addLinkingTokensArrayToSymbol(":_:", new String[] {"R", "U", "L"});

        this.addLinkingTokensArrayToSymbol(":_|", new String[] {"U", "L"});
        this.addLinkingTokensArrayToSymbol(":^|", new String[] {"D", "L"});
        this.addLinkingTokensArrayToSymbol("|^:", new String[] {"D", "R"});
        this.addLinkingTokensArrayToSymbol("|_:", new String[] {"U", "R"});
        this.addLinkingTokensArrayToSymbol("=", new String[] {"R", "L"});
        this.addLinkingTokensArrayToSymbol("| |", new String[] {"U", "D"});

        this.addLinkingTokensArrayToSymbol("|_|", new String[] {"U"});
        this.addLinkingTokensArrayToSymbol(":=|", new String[] {"L"});
        this.addLinkingTokensArrayToSymbol("|^|", new String[] {"D"});
        this.addLinkingTokensArrayToSymbol("|=:", new String[] {"R"});

        this.addLinkingTokensArrayToSymbol("#", new String[] {});
    }
}
