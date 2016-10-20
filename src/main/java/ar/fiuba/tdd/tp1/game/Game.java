package ar.fiuba.tdd.tp1.game;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteLinker;
import ar.fiuba.tdd.tp1.graph.linker.Linker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import ar.fiuba.tdd.tp1.graph.linksmanager.LinksManager;
import ar.fiuba.tdd.tp1.set.CellSet;
import ar.fiuba.tdd.tp1.utilities.InputValidator;
import ar.fiuba.tdd.tp1.utilities.TokenTranslate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by lucas on 10/10/16.
 */
public class Game {
    private GameBoard gameBoard;
    //private TokenTranslate traslate;
    private Collection<CellSet> cellStableSets = new ArrayList<>();
    private InputValidator inputValidator;
    //private VariableSetGenerator setGenerator;
    //private Collection<CellSet> cellVariableSets; //TODO: PARA LOS CONJUNTOS VARIABLES


    //TODO: DEBE PASARSE COMO PARAMETRO EN EL CONSTRUCTOR
    Linker linker;


    public Game(GameBoard gameBoard, InputValidator inputValidator) {

        try {
            this.inputValidator = inputValidator;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameBoard = gameBoard;

        //this.linker = new ConcreteLinker(this.gameBoard, this.linksManager);
        //this.linker = new ConcreteLinker(this.gameBoard, el grafo);

        this.linker = new ConcreteLinker(this.gameBoard, new LinkingTable());
    }

    //TODO; por como se construye el Game ahora conviene setearlo
    public void setLinker(Linker linker) {
        this.linker = linker;
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
        while (it.hasNext()) {
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
    public boolean addPlay(int rowPosition, int columnPosition, String content) {

        //if ( cellXPosition <= this.gameBoard.getWidth() && cellYPosition <= this.gameBoard.getHeigth() ) {
        if (this.playIsAllowed(rowPosition, columnPosition, content)) {
            gameBoard.setCellValue(rowPosition, columnPosition, content);
            //cellVariableSets = setGenerator.generateSet(gameBoard); //TODO: GENERAR CONJUTNOS VARIABLES
            this.linker.updateLinkableLinks(rowPosition, columnPosition);
        }

        return true; //TODO: CUANDO SE INGRESA UNA JUGADA INVALIDA SE DEBE ADVERTIR AL USUARIO
        //TODO: TAMBIEN, ACA SE DEBEN RE-GENERAR LOS CONJUNTOS VARIABLES
    }

    private boolean playIsAllowed(int rowPosition, int columnPosition, String content) {
        return rowPosition < this.gameBoard.getHeigth() && columnPosition < this.gameBoard.getWidth()
                && rowPosition >= 0 && columnPosition >= 0
                && inputValidator.isAValidInput(content);

    }
}
