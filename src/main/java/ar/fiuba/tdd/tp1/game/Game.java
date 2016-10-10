package ar.fiuba.tdd.tp1.game;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.set.CellSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by lucas on 10/10/16.
 */
public class Game {
    private GameBoard gameBoard;
    private Collection<CellSet> cellStableSets = new ArrayList<>();
    //private Collection<CellSet> cellVariableSets = new ArrayList<>(); //TODO: PARA LOS CONJUNTOS VARIABLES


    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /*  */
    public void addSet(CellSet set) {
        cellStableSets.add(set);
    }

    /* Return True if all Rules checked are True */
    public boolean checkRules() {
        int setCount = cellStableSets.size();
        int setValidCount = setCount;

        boolean setCheck = true;
        Iterator it = cellStableSets.iterator();
        while ( it.hasNext() ) {
            CellSet cellSet = (CellSet) it.next();
            if (!cellSet.check()) {
                setCheck = false;
                setValidCount--;
            }
        }   //TODO: ACA SE CHEQUEAN LUEGO LOS CONJUNTOS VARIABLES
        System.out.println("Set count: " + setCount);
        System.out.println("Valid set count: " + setValidCount);

        return setCheck;
    }

    /* Add a play, Devuelve True si la suma de todas las reglas validas es menor o igual
     * que antes de ingresar la jugada  */
    public boolean addPlay(int cellXPosition, int cellYPosition, String content) {
        gameBoard.setCellValue(cellXPosition, cellYPosition, content);
        return true; //TODO: CUANDO SE INGRESA UNA JUGADA INVALIDA SE DEBE ADVERTIR AL USUARIO
                    //TODO: TAMBIEN, ACA SE DEBEN RE-GENERAR LOS CONJUNTOS VARIABLES
    }
}
