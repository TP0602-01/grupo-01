package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.rule.*;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalOperator;
import ar.fiuba.tdd.tp1.rule.utilities.ArithmeticalRuleOperators;
import ar.fiuba.tdd.tp1.rule.utilities.ComparisonOperator;
import ar.fiuba.tdd.tp1.utilities.ParserHelper;

public enum RuleCreator {

    CIRCUIT_COUNT_CREATOR("circuit") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new ExistCircuitRule(Graph.getSingleInstance(), Integer.parseInt(value));
        }
    },

    ESTATE_CORRECT_CREATOR("estate_correct") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new EstateCorrectRule(board, Graph.getSingleInstance());
        }

    },

    NO_REPETITION_RULE_CREATOR("no_rep") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new NoRepetitionRule(value);
        }
    },

    MULT_RULE_CREATOR("mult") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            int expectedValue = Integer.parseInt(value);
            int initAcumulator = 1;

            ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(
                    ArithmeticalOperator.MULT, ComparisonOperator.EQUAL,
                    ComparisonOperator.LESS
            );

            return new AccumulatorRule(expectedValue, operators, initAcumulator);
        }
    },

    SUM_RULE_CREATOR("sum") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            int expectedValue = Integer.parseInt(value);
            int initAcumulator = 0;

            ArithmeticalRuleOperators operators = new ArithmeticalRuleOperators(
                    ArithmeticalOperator.ADDITION, ComparisonOperator.EQUAL,
                    ComparisonOperator.LESS
            );

            return new AccumulatorRule(expectedValue, operators, initAcumulator);
        }
    },

    EMPTY_CELLS_COUNT_CREATOR("expected_data_count") {
        @Override
        public Rule createRule(String value, GameBoard board) {
            return new QuantityCorrectConnectionRegionRule(ParserHelper.toInteger(value));

        }

    },

    CONNECTED_GRAPH_COUNT_CREATOR("conn_graph") {
        @Override
        public Rule createRule(String count, GameBoard ignored) {
            return new ConnectedGraphsCountRule(ParserHelper.toInteger(count));
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
    };

    private static final String DELIMITER = "_";

    public final String stringRepresentation;

    RuleCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }


    public abstract Rule createRule(String value, GameBoard board);


}
