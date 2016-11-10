package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.utilities.ParserHelper;

import java.util.ArrayList;

/*
 * Rule creator represent a Factory Method that can create
 * different rule types.
 *
 */
public enum RuleCreator {

    CIRCUIT_COUNT_CREATOR("circuit") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new ExistCircuitRule(Graph.getSingleInstance(), Integer.parseInt(value));
        }
    },

    ESTATE_CORRECT_CREATOR("estate_correct") {
        @Override
        public Rule createRule(String numberOfRows, GameBoard ignored) {
            //return new EstateCorrectRule(board, Graph.getSingleInstance());
            return new EstateCorrectRule(getIntegerFromString(numberOfRows));
        }
    },

    NO_REPETITION_RULE_CREATOR("no_rep") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new NoRepetitionRule();
        }
    },

    SUM_RULE_CREATOR("sum") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            int expectedValue = Integer.parseInt(value);
            int initSum = 0;

            ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(
                    ArithmeticalOperator.ADDITION, ComparisonOperator.EQUAL,
                    ComparisonOperator.LESS
            );
            return new AccumulatorRule(expectedValue, operators, initSum);
        }
    },

    MULT_RULE_CREATOR("mult") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            int expectedValue = Integer.parseInt(value);
            int initProduct = 1;

            ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(
                    ArithmeticalOperator.MULT, ComparisonOperator.EQUAL,
                    ComparisonOperator.LESS
            );
            AccumulatorRule acummRule = new AccumulatorRule(expectedValue, operators, initProduct);
            return acummRule;
        }
    },

    EMPTY_CELLS_COUNT_CREATOR("expected_data_count") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            QuantityCorrectConnectionRegionRule quantityRule = new QuantityCorrectConnectionRegionRule(ParserHelper.toInteger(value));
            return quantityRule;
        }
    },

    CONNECTED_GRAPH_COUNT_CREATOR("conn_graph") {
        @Override
        public Rule createRule(String counter, GameBoard ignored) {
            Rule rule = new ConnectedGraphsCountRule(ParserHelper.toInteger(counter));
            return rule;
        }
    },

    EDGES_COUNT_CREATOR("edges_count") {
        @Override
        public Rule createRule(String count, GameBoard gameBoard) {
            return new EdgesCountRule(Integer.valueOf(count));
        }
    },

    LINKS_COUNT_CREATOR("links_count") {
        @Override
        public Rule createRule(String value, GameBoard gameBoard) {
            return new LinksCountRule(Integer.parseInt(value));
        }
    },

    CLUSTERS_SIZES_CREATOR("clusters_sizes") {
        @Override
        public Rule createRule(String value, GameBoard gameBoard) {
            ArrayList<Integer> list = new ArrayList<>();
            for (String s : value.split(",")) {
                list.add(Integer.parseInt(s));
            }
            return new ClustersSizesRule(list);
        }
    };

    private static final String DELIMITER = "_";

    public final String stringRepresentation;

    RuleCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public int getIntegerFromString(String string) {
        return ParserHelper.toInteger(string);
    }

    public abstract Rule createRule(String value, GameBoard board);
}
