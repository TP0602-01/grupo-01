package ar.fiuba.tdd.tp1.model.factory;


import ar.fiuba.tdd.tp1.factory.WalkFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.walk.Walk;
import org.junit.Test;

public class WalkFactoryTests {

    WalkFactory walkFactory = new WalkFactory();


    @Test
    public void creatingAWalkWithAnUndefinedTypeMustReturnNull() {
        Walk walk = walkFactory.create(new GameBoard(1, 1), "asdasdasd");
    }

}
