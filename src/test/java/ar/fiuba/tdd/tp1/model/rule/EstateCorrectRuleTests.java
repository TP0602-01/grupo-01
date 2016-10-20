package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.EstateCorrectRule;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
/* */
public class EstateCorrectRuleTests {

    @Test
    public void estateCorrectGraphOneCell() {
        GameBoard gameBoard = new GameBoard(3, 2);

        /* creo un grupo1 de celdas */
        Cell a1 = gameBoard.getCell(0,0);
        Cell a2 = gameBoard.getCell(0,1);
        Cell a3 = gameBoard.getCell(1,0);
        Cell a4 = gameBoard.getCell(1,1);
        Graph group1 = new Graph(a1, a2, a3, a4);

        /* creo grupo2 de celdas */
        Cell a5 = gameBoard.getCell(0,2);
        Cell a6 = gameBoard.getCell(1,2);
        Graph group2 = new Graph(a5, a6);

        Graph graphUniversal = new Graph();
        graphUniversal.addDirectedLinkBetween(a1, a2);
        graphUniversal.addDirectedLinkBetween(a2, a3);
        graphUniversal.addDirectedLinkBetween(a3, a4);
        graphUniversal.addDirectedLinkBetween(a4, a1);
        EstateCorrectRule rule = new EstateCorrectRule(gameBoard,graphUniversal);
        assertTrue(rule.check());
    }
}
