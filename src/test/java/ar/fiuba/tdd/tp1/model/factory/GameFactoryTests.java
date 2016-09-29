package ar.fiuba.tdd.tp1.model.factory;


import ar.fiuba.tdd.tp1.factory.GameFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GameFactoryTests {
    GameFactory gameFactory= new GameFactory();

    @Test
    public void creatingAGameBoardMustReturnAGameBoard() {
        GameBoard gameBoard = gameFactory.createGameBoard(1,1);
        assertTrue(gameBoard instanceof GameBoard);
    }

}
