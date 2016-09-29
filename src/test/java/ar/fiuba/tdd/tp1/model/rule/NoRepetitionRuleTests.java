package ar.fiuba.tdd.tp1.model.rule;


import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.walk.Walk;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class NoRepetitionRuleTests {

    NoRepetitionRule noRepetitionRule;
    RuleTestUtilities utilities = new RuleTestUtilities();

    @Test
    public void checkingTheRuleInACollectionOfCellsThatDoNotHaveRepeatedElementsMustReturnTrue(){
        String[][] cellData = {{"1" ,"2","3"}};
        Walk walkMock = utilities.createAWalkMock(0,0,cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        noRepetitionRule = new NoRepetitionRule(cellAsString,walkMock);
        assertTrue(noRepetitionRule.check());
    }

    @Test
    public void ifThereAreRepeatedElementsCheckMustReturnFalse(){
        String[][] cellData = {{"1" ,"2","1"}};
        Walk walkMock = utilities.createAWalkMock(0,0,cellData);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        noRepetitionRule = new NoRepetitionRule(cellAsString,walkMock);
        assertFalse(noRepetitionRule.check());
    }
}
