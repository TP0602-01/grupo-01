package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class PlayAction implements InputCellAction {

    private GamePlay lastPlay;
    private Game game;
    private InputCellData data;

    public PlayAction(GamePlay lastPlay, Game game, InputCellData data) {
        this.lastPlay = lastPlay;
        this.game = game;
        this.data = data;
    }

    @Override
    public void act() {
        lastPlay.setStatus(game.addPlay(data.getIndexI(), data.getIndexJ(), data.getInputData()));
    }
}
