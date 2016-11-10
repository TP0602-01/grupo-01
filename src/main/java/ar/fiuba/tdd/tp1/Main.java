package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.utilities.GameParser;
import ar.fiuba.tdd.tp1.utilities.ViewParser;
import ar.fiuba.tdd.tp1.view.BoardView;

public class Main {

    private static final String getFilesPath(String []args) {
        String folder = "./src/main/java/ar/fiuba/tdd/tp1/game_files/";

        if (args.length == 0) {
            /* default value */
            return folder + "kakuro";
        } else {
            /* value passed in command line */
            return folder + args[0];
        }
    }

    public static void main(String[] args) {

        try {
            String filesPath = getFilesPath(args);
            GameParser parser = new GameParser(filesPath);

            String filePlaysOutput = "./src/main/java/ar/fiuba/tdd/tp1/game_files/output.json";

            Game game = parser.getGame();
            GameBoardController controller = new GameLoop(game, filePlaysOutput);

            ViewParser viewParser = new ViewParser(filesPath + "/view.json",
                    game.getGameBoard());
            viewParser.parseViewObjects();
            BoardView boardView = viewParser.getBoardView();
            boardView.update();
            controller.start();

            if (game.checkRules()) {
                System.out.println("Game over, you win!");
            } else {
                System.out.println("Game over, you loose");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





