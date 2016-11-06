package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.utilities.GameParser;
import ar.fiuba.tdd.tp1.view.BoardView;

public class Main {

    public static void main(String[] args) {
        try {
            GameParser parser = new GameParser("./src/main/java/ar/fiuba/tdd/tp1/game_files/sudoku");

            String filePlaysOutput = "./src/main/java/ar/fiuba/tdd/tp1/game_files/output.json";

            parser.parseContent();

            Game game = parser.getGame();

            BoardView view = parser.getView();
            GameBoardController controller = new GameLoop(game, filePlaysOutput);


            view.update();  //TODO: UPDATEAR LA VIEW DENTRO DE GAME O EN OTRO LADO
            controller.start();

            if (game.checkRules()) {
                // view.showMessage("Game over, you win!");
                System.out.println("Game over, you win!");
            } else {
                // view.showMessage("Game over, you loose");
                System.out.println("Game over, you loose");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





