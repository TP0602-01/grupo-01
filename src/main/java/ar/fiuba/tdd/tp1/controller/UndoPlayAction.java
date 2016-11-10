package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class UndoPlayAction implements InputCellAction {

    private GamePlay lastPlay;
    private Game game;

    public UndoPlayAction(GamePlay lastPlay, Game game) {
        this.lastPlay = lastPlay;
        this.game = game;
    }

    @Override
    public void act() {
        lastPlay.setStatus(game.undoPlay());
    }
}
