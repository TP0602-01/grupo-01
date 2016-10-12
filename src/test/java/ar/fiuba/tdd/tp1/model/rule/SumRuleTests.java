/*package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.rule.AccumulatorRule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.walk.Walk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;


import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class SumRuleTests {

    AccumulatorRule sumRule;
    RuleTestUtilities utilities = new RuleTestUtilities();


    @Test
    public void theCheckMethodMustReturnTrueWhenTheSumIsCorrect() {
        String[][] cellData = {{"1", "2"}};
        Walk walkMock = utilities.createAWalkMock(0, 0, cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");


        sumRule = new AccumulatorRule(cellAsString, walkMock, 3,
                new ArithmeticalRuleOperators(ArithmeticalOperator.ADDITION,
                        ComparisonOperator.EQUAL, ComparisonOperator.LESS));
        assertTrue(sumRule.check());
    }

    @Test
    public void theCheckMethodMustReturnFalseIfTheSumValueIsDifferentToTheCompareValue() {
        String[][] cellData = {{"1", "2", "3"}};
        Walk walkMock = utilities.createAWalkMock(0, 0, cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        sumRule = new AccumulatorRule(cellAsString, walkMock, 1,
                new ArithmeticalRuleOperators(ArithmeticalOperator.ADDITION,
                        ComparisonOperator.EQUAL, ComparisonOperator.LESS));
        assertFalse(sumRule.check());
    }

}*/
