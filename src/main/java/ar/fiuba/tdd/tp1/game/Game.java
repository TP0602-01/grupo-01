package ar.fiuba.tdd.tp1.game;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteLinker;
import ar.fiuba.tdd.tp1.graph.linker.Linker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import ar.fiuba.tdd.tp1.set.CellSet;
import ar.fiuba.tdd.tp1.utilities.InputValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/*
 * This class represent a game with a game board and a collection
 * of sets. It provide a way to add plays into the board and
 * to check if the game is win (if all sets have all rules checked)
 *
 */
public class Game {
    private GameBoard gameBoard;
    private Collection<CellSet> cellStableSets = new ArrayList<>();
    private InputValidator inputValidator;
    private List<Play> playList;
    Linker linker;


    public Game(GameBoard gameBoard, InputValidator inputValidator) {
        try {
            this.inputValidator = inputValidator;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.gameBoard = gameBoard;
        this.linker = new ConcreteLinker(this.gameBoard, new LinkingTable());
        this.playList = new ArrayList<Play>();
    }


    /*
     * Set Linker to Game.
     *
     */
    public void setLinker(Linker linker) {
        this.linker = linker;
    }

    /*
     * Add Set to Game.
     *
     */
    public void addSet(CellSet set) {
        cellStableSets.add(set);
    }


    /*
     * Return True if all Rules checked are True
     *
     */
    public boolean checkRules() {
        int setCount = cellStableSets.size();
        int setValidCount = validSetCount();

        System.out.println("Set count: " + setCount);
        System.out.println("Valid set count: " + setValidCount);

        return (setCount == setValidCount);
    }

    /*
     * Return the valid set count
     *
     */
    private int validSetCount() {
        int setValidCount = cellStableSets.size();

        Iterator it = cellStableSets.iterator();
        while (it.hasNext()) {
            CellSet cellSet = (CellSet) it.next();
            if (!cellSet.check()) {
                setValidCount--;
            }
        }
        return setValidCount;
    }


    /*
     * Add a play, Return True if all sets are true
     *
     */
    public boolean addPlay(int rowPosition, int columnPosition, String content) {

        if (this.isPlayAllowed(rowPosition, columnPosition, content)) {
            Cell cell = gameBoard.getCell(rowPosition, columnPosition);
            String cellContent = cell.getData();

            Play play = new Play(rowPosition, columnPosition, cellContent);
            playList.add(play);
            int previousValidSetCount = validSetCount();
            gameBoard.setCellValue(rowPosition, columnPosition, content);
            this.linker.updateLinkableLinks(rowPosition, columnPosition);

            int afterValidSetCount = validSetCount();
            if (previousValidSetCount > afterValidSetCount + 2) {
                playList.remove(playList.size() - 1);
                gameBoard.setCellValue(rowPosition, columnPosition, cellContent);
                this.linker.updateLinkableLinks(rowPosition, columnPosition);
                System.out.println("Invalid Play");
            }
            return true;
        }

        return false;
    }

    /*
     * Return Gameboard
     *
     */
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }


    /**
     * Check if play is valid.
     *
     */
    private boolean isPlayAllowed(int rowPosition, int columnPosition, String content) {
        return rowPosition < this.gameBoard.getHeigth() && columnPosition < this.gameBoard.getWidth()
                && rowPosition >= 0 && columnPosition >= 0
                && inputValidator.isAValidInput(content);
    }

    /*
     * Undo Play
     *
     */
    public boolean undoPlay() {
        if (playList.isEmpty()) {
            return false;
        }
        int lastPosition = playList.size() - 1;
        Play lastPlay = playList.get(lastPosition);
        gameBoard.setCellValue(lastPlay.getRowPosition(), lastPlay.getColumPosition(), lastPlay.getContent());
        this.linker.updateLinkableLinks(lastPlay.getRowPosition(), lastPlay.getColumPosition());
        playList.remove(lastPosition);
        return true;
    }
}
