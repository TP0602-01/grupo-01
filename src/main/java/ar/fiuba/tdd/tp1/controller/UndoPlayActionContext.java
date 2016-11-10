package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class UndoPlayActionContext {

    private GamePlay lastPlay;
    private Game game;

    public UndoPlayActionContext(GamePlay lastPlay, Game game) {
        this.lastPlay = lastPlay;
        this.game = game;
    }

    public GamePlay getLastPlay() {
        return lastPlay;
    }

    public Game getGame() {
        return game;
    }

}
