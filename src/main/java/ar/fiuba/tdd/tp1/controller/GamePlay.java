package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;
import org.json.simple.JSONObject;

public class GamePlay {
    private int number;
    private boolean playStatus;
    private boolean gameStatus;
    private boolean gameStopped;

    public GamePlay() {
        number = 0;
        playStatus = true;
        gameStatus = false;
        gameStopped = false;
    }

    public void increaseNumber() {
        ++number;
    }

    public JSONObject serialize() {
        JSONObject play = new JSONObject();
        play.put("number", number);
        play.put("playStatus", playStatus);
        play.put("gameStatus", gameStatus);
        return play;
    }

    public void checkRules(Game game) {
        this.gameStatus = game.checkRules();
    }

    public boolean gameOver() {
        return gameStopped || gameStatus;
    }

    public void flagAsStopped() {
        gameStopped = true;
    }

    public void setStatus(boolean status) {
        this.playStatus = status;
    }
}
