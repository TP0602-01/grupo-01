package ar.fiuba.tdd.tp1.controller;

public class StopGameAction implements InputCellAction {

    private GamePlay lastPlay;

    public StopGameAction(StopActionContext context) {
        this.lastPlay = context.getLastPlay();
    }

    @Override
    public void act() {
        lastPlay.flagAsStopped();
    }
}
