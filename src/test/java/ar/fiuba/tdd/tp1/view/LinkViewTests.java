package ar.fiuba.tdd.tp1.view;

import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import org.junit.Test;

/**
 * Created by User on 15/10/2016.
 */
public class LinkViewTests {

    @Test
    public void SimpleLinkViewSimulation() {
        GameBoard gameBoard = new GameBoard(3, 3);
        Graph graph = new Graph();
        HorizontalLinksView horizontalLinksView = new HorizontalLinksView(gameBoard, graph);
        BoardView boardView = new BoardView(gameBoard, horizontalLinksView);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardView.addCellViewIn(new VertexView(), row, col);
            }
        }
        graph.addNotDirectedLinkBetween(gameBoard.getCell(1, 1), gameBoard.getCell(1, 2));
        graph.addNotDirectedLinkBetween(gameBoard.getCell(2, 0), gameBoard.getCell(2, 1));
        boardView.update();
    }

    @Test
    public void VerticalLinkViewSimulation() {
        GameBoard gameBoard = new GameBoard(3, 3);
        Graph graph = new Graph();
        HorizontalLinksView horizontalLinksView = new HorizontalLinksView(gameBoard, graph);
        VerticalLinksView verticalView = new VerticalLinksView(gameBoard, graph);
        BoardView boardView = new BoardView(gameBoard, horizontalLinksView, verticalView);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                boardView.addCellViewIn(new VertexView(), row, col);
            }
        }
        graph.addNotDirectedLinkBetween(gameBoard.getCell(1, 1), gameBoard.getCell(1, 2));
        graph.addNotDirectedLinkBetween(gameBoard.getCell(1, 1), gameBoard.getCell(2, 1));
        graph.addNotDirectedLinkBetween(gameBoard.getCell(1, 0), gameBoard.getCell(2, 0));
        graph.addNotDirectedLinkBetween(gameBoard.getCell(1, 2), gameBoard.getCell(2, 2));
        boardView.upgradedUpdate();
    }

}
