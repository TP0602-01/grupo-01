package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.walk.Walk;

import java.util.Collection;


interface RuleCreator {
    IRule createRule(Walk walkObject, Collection<String> cellPositions);
}
