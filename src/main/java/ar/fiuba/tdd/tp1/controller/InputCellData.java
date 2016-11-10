package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;


/*
 * Input Cell Data represent a cell thtt receive an string line
 * (user input), parse it and can be access to parse content.
 *
 */
class InputCellData {
    private String playType;

    private String[] splited;

    InputCellData(String line) throws InvalidInputException {
        splited = line.split(" ");
        if (splited.length == 0) {
            throw new InvalidInputException("Input vacío");
        }
        playType = splited[0];
    }

    String getPlayType() {
        return playType;
    }

    /*
     * Action included into user play. Return action type
     *
     */
    public InputCellAction generateAction(Game game, GamePlay lastPlay) throws InvalidInputException {
        if (getPlayType().equals("stop")) {
            return new StopGameAction(new StopActionContext(lastPlay));
        } else if (getPlayType().equals("undo")) {
            return new UndoPlayAction(new UndoPlayActionContext(lastPlay, game));
        } else if (getPlayType().equals("play")) {
            return new PlayAction(new PlayActionContext(lastPlay, game, splited));
        } else {
            throw new InvalidInputException("Keyword invalida");
        }
    }

}
