package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class UndoPlayAction implements InputCellAction {

    private GamePlay lastPlay;
    private Game game;

    public UndoPlayAction(UndoPlayActionContext context) {
        this.lastPlay = context.getLastPlay();
        this.game = context.getGame();
    }

    @Override
    public void act() {
        lastPlay.setStatus(game.undoPlay());
    }
}
