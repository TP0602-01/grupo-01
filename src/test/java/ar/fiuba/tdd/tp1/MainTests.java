package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.utilities.GameParser;
import ar.fiuba.tdd.tp1.view.BoardView;
import org.junit.Test;

import java.io.FileInputStream;

import static junit.framework.TestCase.assertTrue;

public class MainTests {

    boolean autoPlayGame(String input, String structure, String rules) {
        try {
            boolean win = false;

            System.setIn(new FileInputStream(input));

            GameParser parser = new GameParser(structure, rules);
            parser.parseGameStructure();
            parser.parseGameRules();

            GameBoard gameBoard = parser.getBoard();
            BoardView view = parser.getView();
            GameBoardController controller = new GameLoop(gameBoard);

            view.update();
            controller.start();

            if (gameBoard.checkRules()) {
                win = true;
                view.showMessage("Game over, you win!");
            } else {
                view.showMessage("Game over, you loose");
            }
            return win;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


    @Test
    public void autoPlayingKakuroToWin() {

        String input = "./src/test/java/ar/fiuba/tdd/tp1/testInput.txt";
        String structure = "./src/main/java/ar/fiuba/tdd/tp1/kakoruStructure.json";
        String rules = "./src/main/java/ar/fiuba/tdd/tp1/kakoruRules.json";

        assertTrue(autoPlayGame(input, structure, rules));


    }

    @Test
    public void autoPlayingSudokuToWin() {
        String input = "./src/test/java/ar/fiuba/tdd/tp1/sudokuSolution.txt";
        String structure = "./src/main/java/ar/fiuba/tdd/tp1/structure.json";
        String rules = "./src/main/java/ar/fiuba/tdd/tp1/rules.json";

        assertTrue(autoPlayGame(input, structure, rules));
    }

}
