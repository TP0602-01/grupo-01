package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.utilities.GameParser;
import ar.fiuba.tdd.tp1.view.BoardView;

public class Main {

    public static void main(String[] args) {
        try {
            GameParser parser = new GameParser(
                    "./src/main/java/ar/fiuba/tdd/tp1/kakoruStructure.json",
                    "./src/main/java/ar/fiuba/tdd/tp1/kakoruRules.json");
            parser.parseGameStructure();
            parser.parseGameRules();

            GameBoard gameBoard = parser.getBoard();
            BoardView view = parser.getView();
            GameBoardController controller = new GameLoop(gameBoard);

            view.update();
            controller.start();

            if (gameBoard.checkRules()) {
                view.showMessage("Game over, you win!");
            } else {
                view.showMessage("Game over, you loose");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





