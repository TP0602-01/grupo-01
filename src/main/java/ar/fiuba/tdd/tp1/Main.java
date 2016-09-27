package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.rule.IRule;
import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.utilities.Observable;
import ar.fiuba.tdd.tp1.utilities.Observer;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.DataCellView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        /*System.out.println("This is just a tp1 project");
        BoardView boardView = new BoardView();
        InputCell dataCell1 = new InputCell();
        InputCell dataCell2 = new InputCell();
        InputCell dataCell3 = new InputCell();

        DataCellView dataCellView1 = new DataCellView(dataCell1);
        DataCellView dataCellView2 = new DataCellView(dataCell2);
        DataCellView dataCellView3 = new DataCellView(dataCell3);

        boardView.addCellView(dataCellView1);
        boardView.addCellView(dataCellView2);
        boardView.addCellView(dataCellView3);

        dataCell1.setData(8);
        dataCell2.setData(5);
        boardView.update();

        System.out.println("dataCell3 is updated");
        dataCell3.setData(2);
        boardView.update();*/

        // PARAMETROS QUE SE TOMAN DE ARCHIVO
        int boardWidth = 9;
        int boardHeight = 9;


        // Initialize Structure Game
        boolean gameFinished = false;

        // Board
        GameBoard gameBoard = new GameBoard(boardWidth, boardHeight);
        BoardView boardView = new BoardView(gameBoard);

        // Walks
        // Walk walk = new Walk()   PARA TODAS LOS TIPOS DE WALKS => FACTORY METHOD
        // walk.setBoard()

        // Rules
        // IRule rule = new Rule(1,2, walk) PARA TODOS LAS REGLAS QUE HAYA => FACTORY METHOD

        // gameBoard.addCell(x, y, cell) PARA TODAS LAS CELDAS => FACTORY METHOD
        // gameBoard.addRule(rule) PARA TODAS LAS REGLAS => FACTORY METHOD

        gameBoard.registerObserver(boardView);

        while (!gameFinished) {
            if (gameBoard.checkRules()) {
                gameFinished = true;
                continue;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter a movement: ");
            try {
                String input = bufferedReader.readLine();
                // CHECKEAMOS EL INPUT
                //Cell newCell = Cell();    CREAMOS LA CELDA => FACTORY METHOD
                //gameBoard.addCell(cell);
            } catch (IOException e) {
                return;
            }
        }
        System.out.print("The Game have finished, you Wiiin!!!");
    }
}
