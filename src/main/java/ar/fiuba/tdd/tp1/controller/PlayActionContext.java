package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

public class PlayActionContext {

    private GamePlay lastPlay;
    private Game game;
    private int indexI;
    private int indexJ;
    private String data;


    public PlayActionContext(GamePlay lastPlay, Game game, String[] splited) {
        this.lastPlay = lastPlay;
        this.game = game;
        indexI = Integer.parseInt(splited[1]);
        indexJ = Integer.parseInt(splited[2]);
        data = splited[3];

        System.out.println(splited[0].concat(" ")
                .concat(splited[1]).concat(" ")
                .concat(splited[2]).concat(" ")
                .concat(data));
    }

    public GamePlay getLastPlay() {
        return lastPlay;
    }

    public Game getGame() {
        return game;
    }

    public int getIndexI() {
        return indexI - 1;
    }

    public int getIndexJ() {
        return indexJ - 1;
    }

    public String getData() {
        return data;
    }
}
