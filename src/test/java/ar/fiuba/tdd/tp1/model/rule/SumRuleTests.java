package ar.fiuba.tdd.tp1.model.rule;

import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.walk.Walk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;


import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


public class SumRuleTests {

    SumRule sumRule;
    RuleTestUtilities utilities = new RuleTestUtilities();


    @Test
    public void theCheckMethodMustReturnTrueWhenTheSumIsCorrect() {
        String[][] cellData = {{"1", "2"}};
        Walk walkMock = utilities.createAWalkMock(0, 0, cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        sumRule = new SumRule(cellAsString, walkMock, 3);
        assertTrue(sumRule.check());
    }

    @Test
    public void theCheckMethodMustReturnFalseIfTheSumValueIsDifferentToTheCompareValue() {
        String[][] cellData = {{"1", "2", "3"}};
        Walk walkMock = utilities.createAWalkMock(0, 0, cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        sumRule = new SumRule(cellAsString, walkMock, 1);
        assertFalse(sumRule.check());
    }

}
