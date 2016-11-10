package ar.fiuba.tdd.tp1.controller;

public class StopGameAction implements InputCellAction {
    private GamePlay lastPlay;

    public StopGameAction(GamePlay lastPlay) {
        this.lastPlay = lastPlay;
    }

    @Override
    public void act() {
        lastPlay.flagAsStopped();
    }
}
