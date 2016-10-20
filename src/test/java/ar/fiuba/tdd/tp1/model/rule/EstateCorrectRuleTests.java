package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.EstateCorrectRule;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
/**
 */
public class EstateCorrectRuleTests {
    @Test
    public void estateCorrectGraphOneCell(){
        GameBoard gameBoard = new GameBoard(3,2);
        Cell a1 = gameBoard.getCell(0,0);
        Cell a2 = gameBoard.getCell(0,1);
        Cell a3 = gameBoard.getCell(1,0);
        Cell a4 = gameBoard.getCell(1,1);
        Cell a5 = gameBoard.getCell(0,2);
        Cell a6 = gameBoard.getCell(1,2);
        /*creo grupo2 de celdas*/
        Graph group2 = new Graph();
        group2.addCell(a5);
        group2.addCell(a6);
        Graph graphUniversal = new Graph();
        /*creo un grupo1 de celdas*/
        Graph group1 = new Graph();
        group1.addCell(a1);
        group1.addCell(a2);
        group1.addCell(a3);
        group1.addCell(a4);
        graphUniversal.addDirectedLinkBetween(a1,a2);
        graphUniversal.addDirectedLinkBetween(a2,a3);
        graphUniversal.addDirectedLinkBetween(a3,a4);
        graphUniversal.addDirectedLinkBetween(a4,a1);
        EstateCorrectRule rule = new EstateCorrectRule(gameBoard,graphUniversal);
        assertTrue(rule.check());
    }
}
