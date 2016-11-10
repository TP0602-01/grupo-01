package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;

class InputCellData {
    private int indexI;
    private int indexJ;
    private String data;
    private String playType;

    InputCellData(String line) {
        String[] splited = line.split(" ");
        playType = splited[0];
        indexI = Integer.parseInt(splited[1]);
        indexJ = Integer.parseInt(splited[2]);
        data = splited[3];

        System.out.println(playType.concat(" ")
                .concat(splited[1]).concat(" ")
                .concat(splited[2]).concat(" ")
                .concat(data));
    }

    int getIndexI() {
        return indexI - 1;
    }

    int getIndexJ() {
        return indexJ - 1;
    }

    String getInputData() {
        return data;
    }

    String getPlayType() {
        return playType;
    }

    public InputCellAction generateAction(Game game, GamePlay lastPlay) throws InvalidInputException {
        if (getPlayType().equals("stop")) {
            return new StopGameAction(lastPlay);
        } else if (getPlayType().equals("undo")) {
            return new UndoPlayAction(lastPlay, game);
        } else if (getPlayType().equals("play")) {
            return new PlayAction(lastPlay, game, this);
        } else {
            throw new InvalidInputException("Keyword invalida");
        }
    }
}
