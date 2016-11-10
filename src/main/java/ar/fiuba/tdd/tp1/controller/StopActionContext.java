package ar.fiuba.tdd.tp1.controller;

public class StopActionContext {

    private GamePlay lastPlay;

    public StopActionContext(GamePlay lastPlay) {
        this.lastPlay = lastPlay;
    }

    public GamePlay getLastPlay() {
        return lastPlay;
    }
}
