/*package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.direction.RightDirection;
import ar.fiuba.tdd.tp1.rule.*;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;*/

/**
 * Created by juanma on 25/09/16.
 */
/*public class DirectionalRulesTests {

    @Test
    public void testCorrectSumRuleCheckedOnACompleteRowInRightDirection() {
        //Row construction simulated
        InputCell a1, a2, a3, a4;
        a1 = new InputCell();
        a2 = new InputCell();
        a3 = new InputCell();
        a4 = new InputCell();

        a1.setRightCell(a2);
        a2.setRightCell(a3);
        a3.setRightCell(a4);
        a4.setRightCell(null);//null because it is the last cell in the row


        RightDirection rightDirection = new RightDirection();
        BaseRule sumRule = new SumRule(a1, rightDirection, 10);

        //Simulated data input
        a1.setData(1);
        a2.setData(2);
        a3.setData(3);
        a4.setData(4);

        assertTrue(sumRule.directionalCheck());
    }

    @Test
    public void testViolatedSumRuleCheckedOnACompleteRowInRightDirection() {
        //TODO: refactor
        //Row construction simulated
        InputCell a1, a2, a3, a4;
        a1 = new InputCell();
        a2 = new InputCell();
        a3 = new InputCell();
        a4 = new InputCell();

        a1.setRightCell(a2);
        a2.setRightCell(a3);
        a3.setRightCell(a4);
        a4.setRightCell(null);//null because it is the last cell in the row


        RightDirection rightDirection = new RightDirection();
        BaseRule sumRule = new SumRule(a1, rightDirection, 10);

        //Simulated data input
        a1.setData(80);
        a2.setData(16);
        a3.setData(1);
        a4.setData(2);

        assertFalse(sumRule.directionalCheck());
    }

    @Test
    public void testCorrectSumRuleCheckedOnAPortionOfARowInRightDirection() {
        //TODO: refactor
        //Row construction simulated
        InputCell a1, a2, a3, a4, a5, a6;
        a1 = new InputCell();
        a2 = new InputCell();
        a3 = new InputCell();
        a4 = null;
        a5 = new InputCell();
        a6 = new InputCell();

        a1.setRightCell(a2);
        a2.setRightCell(a3);
        a3.setRightCell(null); //a4 simulated as a null (black) cell
        //a4 simulated as black cell
        a5.setRightCell(a6);
        a6.setRightCell(null);//null because it is the last cell in the row

        RightDirection rightDirection = new RightDirection();
        BaseRule sumRule = new SumRule(a1, rightDirection, 6);

        //Glorious ASCII Art representation:
        //| \Sum=6->|a1|a2|a3|#|a5|a6|

        //Simulated data input
        a1.setData(1);
        a2.setData(2);
        a3.setData(3);
        //Cells after the null (black) cell don't affect the outcome
        a5.setData(80);
        a6.setData(666);

        assertTrue(sumRule.directionalCheck());
    }

    @Test
    public void testViolatedSumRuleCheckedOnAPortionOfARowInRightDirection() {
        //TODO: refactor
        //Row construction simulated
        InputCell a1, a2, a3, a4, a5, a6;
        a1 = new InputCell();
        a2 = new InputCell();
        a3 = new InputCell();
        a4 = null;
        a5 = new InputCell();
        a6 = new InputCell();

        a1.setRightCell(a2);
        a2.setRightCell(a3);
        a3.setRightCell(null); //a4 simulated as a null (black) cell
        //a4 simulated as black cell
        a5.setRightCell(a6);
        a6.setRightCell(null);//null because it is the last cell in the row

        RightDirection rightDirection = new RightDirection();
        BaseRule sumRule = new SumRule(a1, rightDirection, 17);

        //Glorious ASCII Art representation:
        //| \Sum=6->|a1|a2|a3|#|a5|a6|

        //Simulated data input
        a1.setData(1);
        a2.setData(2);
        a3.setData(3);
        //Cells after the null (black) cell don't affect the outcome
        a5.setData(5);
        a6.setData(6);

        //17 = 1 + 2 + 3 + 5 + 6 BUT the Rule only sums 1 + 2 + 3 due to the null cell
        assertFalse(sumRule.directionalCheck());
    }
}*/
