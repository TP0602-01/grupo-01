package ar.fiuba.tdd.tp1.graph;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.linkeable.Linkable;
import ar.fiuba.tdd.tp1.graph.linker.ConcreteSquareLinker;
import ar.fiuba.tdd.tp1.graph.linker.LinkingTable;
import ar.fiuba.tdd.tp1.graph.linker.SquareLinker;
import ar.fiuba.tdd.tp1.graph.linksmanager.LinksManager;
import ar.fiuba.tdd.tp1.graph.linksmanager.MapLinkManager;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/* */
public class LinkedGraphTests {

    @Test
    public void testLinkTwoLinkedCells() {
        Linkable a1 = new InputCell("");
        Linkable a2 = new InputCell("");

        LinksManager linksManager = new MapLinkManager();

        linksManager.addDirectedLinkBetween(a1, a2);
        //up to here a1 can go to a2 BUT a2 CANT go to a1

        linksManager.addDirectedLinkBetween(a2, a1);
        //now a1 can go to a2 and a2 can go to a1

        assertTrue(linksManager.linkExistsFromOriginToDestination(a1, a2));
        assertTrue(linksManager.linkExistsFromOriginToDestination(a2, a1));
    }

    @Test
    public void testLinkerSimulationWithGameBoardAndLinksManager() {
        ////////////////////////////////CONFIGURACION DEL BOARD////////////////////
        GameBoard board = new GameBoard(2, 1);
        board.addCell(0, 0, new InputCell());
        board.addCell(0, 1, new InputCell());
        //|a1|a2|
        ///////////////////////////////////////////////////////////////////////////

        ///////////////////////////////CONFIGURACION DEL LINKER////////////////////
        LinksManager linksManager = new MapLinkManager();
        SquareLinker linker = new ConcreteSquareLinker(board, linksManager);
        //Lo que esta en ESPANIOL y los OFFSETS se leerian de la configuracion
        //Los nombres de los Tokens se pueden llamar de cualquier forma, depende de la
        // configuracion, los tokens solo sirven para poder indicar quien se puede
        // linkear con quien
        //Lo seteo para la izquierda, esto :
        int rowOffset = 0;
        int columnOffset = 1;
        String rightOriginToken = "DERECHA";
        Set<String> rightDestinationTokens = new HashSet<>();
        rightDestinationTokens.add("IZQUIERDA");
        linker.setLinkingInfo(rowOffset, columnOffset, rightOriginToken, rightDestinationTokens);
        //Si la celda origen tiene el Token "DERECHA" y la celda de la derecha
        // a la origen tiene el token "IZQUIERDA"
        //entonces se puede establecer un link/arista de la celda origen a la celda destino


        String leftOriginToken = "IZQUIERDA";
        Set<String> leftDestinationTokens = new HashSet<>();
        leftDestinationTokens.add("DERECHA");
        linker.setLinkingInfo(0, -1, leftOriginToken, leftDestinationTokens);
        //////////////////////////////////////////////////////////////////////////////////


        ////////////////////////SIMULACION DEL INPUT DEL USUARIO//////////////////////////
        //LOS TOKENS VALIDOS SERIAN LOS QUE SE INDIQUEN EN EL ARCHIVO DE CONFIGURACION,
        // SE PUEDEN LLAMAR DE CUALQUIER FORMA
        //Esto simularia que el usuario ingresa en la celda a1 el dibujo "---"
        // del country road
        Cell a1 = board.getCell(0, 0);
        Cell a2 = board.getCell(0, 1);

        Set<String> a1InputTokens = new HashSet<>();

        a1InputTokens.add("DERECHA");
        a1InputTokens.add("IZQUIERDA");

        a1.setLinkingTokens(a1InputTokens);
        //Ahora el Linker chequea los nuevos links que puede hacer luego de que el usuario
        // ingresara "---" en a1
        //Luego de haber ingresado un input a a1, antes de que el Linker updatee los Links
        // de a1, a1 NO esta linkeada a a2

        assertFalse(linksManager.linkExistsFromOriginToDestination(a1, a2));

        linker.updateLinkableLinks(0, 0);
        //Luego de updatear, a1 sigue SIN estar linkeada a a2 xq a2 no tiene cargado ningun
        // Token,
        assertFalse(linksManager.linkExistsFromOriginToDestination(a1, a2));

        Set<String> a2InputTokens = new HashSet<>();

        //Esto simularia que el usuario ingresa en la celda a2 el dibujo "¬" del country road
        a2InputTokens.add("IZQUIERDA");
        a2InputTokens.add("ABAJO");
        ////Luego de haber ingresado un input a a2, antes de que el Linker updatee los Links
        // de a2, a2 NO esta linkeada a a1
        assertFalse(linksManager.linkExistsFromOriginToDestination(a1, a2));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a2, a1));

        a2.setLinkingTokens(a2InputTokens);
        //Luego de updatear, a1 y a2 estan linkeados,
        //porque a1 tiene linking Tokens linkeables por DERECHA   con los linking Tokens de a2 y
        //porque a2 tiene linking Tokens linkeables por IZQUIERDA con los linking Tokens de a2
        linker.updateLinkableLinks(0, 0);
        linker.updateLinkableLinks(0, 1);

        assertTrue(linksManager.linkExistsFromOriginToDestination(a1, a2));
        assertTrue(linksManager.linkExistsFromOriginToDestination(a2, a1));

        ////////////////////////////////////////////////////////////////////////////////
    }

    private void createLinkingEntryWithOnlyOneDestinationLinkingToken(
            SquareLinker linker, int rowOffset, int columnOffset,
            String originToken, String destinationToken) {
        Set<String> destinationTokens = new HashSet<>();
        destinationTokens.add(destinationToken);
        linker.setLinkingInfo(rowOffset, columnOffset, originToken, destinationTokens);
    }

    private void createLinkingEntryWithNDestinationLinkingTokens(SquareLinker linker, int rowOffset, int columnOffset, String originToken, String[] destinationToken, int destinationTokensCount) {
        Set<String> destinationTokens = new HashSet<>();
        for (int i = 0; i < destinationTokensCount; i++) {
            destinationTokens.add(destinationToken[i]);
        }
        linker.setLinkingInfo(rowOffset, columnOffset, originToken, destinationTokens);
    }

    @Test
    public void testGokigenNanameConfiguration() {

        GameBoard board = new GameBoard(3, 3);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.addCell(row, col, new InputCell());
            }
        }

        LinksManager linksManager = new MapLinkManager();
        SquareLinker linker = new ConcreteSquareLinker(board, linksManager);

        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "/", "\\");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 1, "/", "/");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "/", "\\");
        //"/", can't be linked in -1, -1 direction
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "/", "\\");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, -1, "/", "/");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "/", "\\");
        //"/", can't be linked in 1, 1 direction

        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "\\", "/");
        //"\", can't be linked in -1, 1 direction
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "\\", "/");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, -1, "\\", "\\");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "\\", "/");
        //"\", can't be linked in 1, -1 direction
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "\\", "/");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 1, "\\", "\\");

        /*     0 1 2
            0 | | | |
            1 | | | |
            2 | | | |
         */
        Cell a00 = board.getCell(0, 0);
        Cell a01 = board.getCell(0, 1);
        Cell a02 = board.getCell(0, 2);
        Cell a10 = board.getCell(1, 0);
        Cell a11 = board.getCell(1, 1);
        Cell a12 = board.getCell(1, 2);
        Cell a20 = board.getCell(2, 0);
        Cell a21 = board.getCell(2, 1);
        Cell a22 = board.getCell(2, 2);

        a00.addLinkingToken("/");
        //a01 has no linking tokens
        a02.addLinkingToken("/");
        a10.addLinkingToken("\\");
        a11.addLinkingToken("/");
        a12.addLinkingToken("\\");
        //a20 has no linking tokens
        a21.addLinkingToken("\\");
        a22.addLinkingToken("\\");

        /*     0 1 2
            0 |/| |/|
            1 |\|/|\|
            2 | |\|\|
        */

        linker.updateLinkableLinks(1, 1);

        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a00));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a01));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a20));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a22));

        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a02));
        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a10));
        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a12));
        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a21));
    }


    @Test
    public void testCountryRoadConfiguration() {
        GameBoard board = new GameBoard(3, 3);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.addCell(row, col, new InputCell());
            }
        }

        LinksManager linksManager = new MapLinkManager();
        SquareLinker linker = new ConcreteSquareLinker(board, linksManager);

        //4 lineas de configuracion por un lado
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, 1, "R", "L");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, -1, 0, "U", "D");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 0, -1, "L", "R");
        createLinkingEntryWithOnlyOneDestinationLinkingToken(linker, 1, 0, "D", "U");

        /*     0 1 2
            0 | | | |
            1 | | | |
            2 | | | |
         */
        Cell a00 = board.getCell(0, 0);
        Cell a01 = board.getCell(0, 1);
        Cell a02 = board.getCell(0, 2);
        Cell a10 = board.getCell(1, 0);
        Cell a11 = board.getCell(1, 1);
        Cell a12 = board.getCell(1, 2);
        Cell a20 = board.getCell(2, 0);
        Cell a21 = board.getCell(2, 1);
        Cell a22 = board.getCell(2, 2);


        /*     0         1     2
            0 ||     ||  |  ||  ¡--||
            1 || --- ||  ¡--||--!  ||
            2 ||     ||     ||     ||
         */


        //Deberia haber una especie de traductor de input a linkingTokens
        //Por ejemplo "¡--" ----> ["D", "R"]

        a01.addLinkingToken("U");
        a01.addLinkingToken("D");

        a02.addLinkingToken("D");
        a02.addLinkingToken("R");

        a10.addLinkingToken("R");
        a10.addLinkingToken("L");

        a11.addLinkingToken("R");
        a11.addLinkingToken("D");

        a12.addLinkingToken("L");
        a12.addLinkingToken("U");

        a21.addLinkingToken("R");
        a21.addLinkingToken("D");


        /*       0       1      2
            0 ||     ||  |  ||  ¡--||
            1 || --- ||  ¡--||--!  ||
            2 ||     ||  ¡--||     ||
         */

        linker.updateLinkableLinks(1, 1);

        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a00));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a01));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a02));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a10));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a20));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a21));
        assertFalse(linksManager.linkExistsFromOriginToDestination(a11, a22));

        assertTrue(linksManager.linkExistsFromOriginToDestination(a11, a12));
    }

    @Test
    public void testAddingAnEntryToALinkingTableAndCheckingThatEntryExistanceMustBeTrue() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "ConfigurableOriginToken";
        String destinationToken = "ConfigurableDestinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertTrue(linkingTable.checkEntryExistance(rowOffset, colOffset, originToken, destinationToken));
    }

    public void testCheckingEntryExistanceOfAnNonExistingEntryMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, "NonExistingOriginToken", "NonExistingDestinationToken"));
    }

    public void testCheckingEntryExistanceOfAnEntryWithAnNonExistingOriginTokenMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, "NonExistingOriginToken", destinationToken));
    }


    public void testCheckingEntryExistanceOfAnEntryWithAnNonExistingDestinationTokenMustBeFalse() {
        LinkingTable linkingTable = new LinkingTable();
        int rowOffset = 1;
        int colOffset = 1;
        String originToken = "originToken";
        String destinationToken = "destinationTokenInChosenOffset";
        linkingTable.addEntry(rowOffset, colOffset, originToken, destinationToken);
        assertFalse(linkingTable.checkEntryExistance(rowOffset, colOffset, originToken, "NonExistingDestinationToken"));
    }
}
