package ar.fiuba.tdd.tp1.model.factory;


import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.model.rule.RuleTestUtilities;
import ar.fiuba.tdd.tp1.rule.AccumulatorRule;
import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.walk.Walk;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class RuleFactoryTests {
    RuleTestUtilities utilities = new RuleTestUtilities();

    @Test
    public void creatingARuleOfTypeNoRepMustReturnANonRepetitionRuleObject() {
        String[][] data = {{"0"}};
        Walk walk = utilities.createAWalkMock(0, 0, data);
        IRule rule = RuleFactory.create(RuleFactory.NO_REPETITION_TYPE, walk, new ArrayList<>());

        assertTrue(rule instanceof NoRepetitionRule);
    }

    @Test
    public void creatingARuleOfTypeSumMustReturnASumRuleObject() {
        String[][] data = {{"1"}};
        Walk walk = utilities.createAWalkMock(0, 0, data);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0_0");
        IRule rule = RuleFactory.create(RuleFactory.SUM_TYPE, walk, cellAsString);

        assertTrue(rule instanceof AccumulatorRule);
    }

    @Test
    public void creatingARuleWithUndefinedTypeMustReturnFalse() {
        String[][] data = {{"1"}};
        Walk walk = utilities.createAWalkMock(0, 0, data);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0_0");
        IRule rule = RuleFactory.create("alskjdlaksjdsakl", walk, cellAsString);

        assertEquals(null, rule);
    }

}
