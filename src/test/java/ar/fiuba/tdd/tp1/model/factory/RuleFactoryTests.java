package ar.fiuba.tdd.tp1.model.factory;


import ar.fiuba.tdd.tp1.factory.RuleCreator;
import ar.fiuba.tdd.tp1.factory.RuleFactory;
import ar.fiuba.tdd.tp1.model.rule.RuleTestUtilities;
import ar.fiuba.tdd.tp1.rule.AccumulatorRule;
import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.rule.NoRepetitionRule;
import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
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
        IRule rule = RuleFactory.create(RuleCreator.NO_REPETITION_RULE_CREATOR.stringRepresentation
                , walk, new ArrayList<>(), null);

        assertTrue(rule instanceof NoRepetitionRule);
    }

    @Test
    public void creatingARuleOfTypeSumMustReturnASumRuleObject() {
        String[][] data = {{"1"}};
        Walk walk = utilities.createAWalkMock(0, 0, data);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0_0");
        ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(ArithmeticalOperator.ADDITION, ComparisonOperator.EQUAL, ComparisonOperator.LESS);
        IRule rule = RuleFactory.create(RuleCreator.ACCUMULATOR_RULE_CREATOR.stringRepresentation, walk, cellAsString, operators);

        assertTrue(rule instanceof AccumulatorRule);
    }

    @Test
    public void creatingARuleWithUndefinedTypeMustReturnNull() {
        String[][] data = {{"1"}};
        Walk walk = utilities.createAWalkMock(0, 0, data);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0_0");
        IRule rule = RuleFactory.create("alskjdlaksjdsakl", walk, cellAsString, null);

        assertEquals(null, rule);
    }

}
