package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class PlayAction implements InputCellAction {

    private GamePlay lastPlay;
    private Game game;
    private int indexI;
    private int indexJ;
    private String inputData;

    public PlayAction(PlayActionContext context) {
        lastPlay = context.getLastPlay();
        game = context.getGame();
        indexI = context.getIndexI();
        indexJ = context.getIndexJ();
        inputData = context.getData();
    }

    @Override
    public void act() throws InvalidInputException {
        lastPlay.setStatus(game.addPlay(indexI, indexJ, inputData));
    }
}
