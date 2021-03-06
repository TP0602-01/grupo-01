package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.utilities.GameParser;

import ar.fiuba.tdd.tp1.view.BoardView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MainTests {

    private static final BoardView boardView = mock(BoardView.class);

    /*
     *  Auto PLay Test.
     *
     */
    boolean autoPlayGame(String playsInput,
                         String playsOutput,
                         String gameFolderPath) {
        try {
            JSONParser jsonParser = new JSONParser();
            String playString = "";
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(playsInput), "UTF-8"));
            JSONArray plays = (JSONArray) jsonObject.get("plays");
            for (int i = 0; i < plays.size(); ++i) {
                playString = parsePlayString((JSONObject) plays.get(i), playString);
            }
            System.out.println(playString);
            InputStream stream = new ByteArrayInputStream(playString.getBytes(StandardCharsets.UTF_8));
            System.setIn(stream);

            GameParser parser = new GameParser(gameFolderPath);
            Game game = parser.getGame();
            GameBoardController controller = new GameLoop(game, playsOutput);

            controller.start();

            return game.checkRules();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     *  Parser Play Json Object and return the play String.
     *
     */
    private String parsePlayString(JSONObject play, String playString) {
        String playType = (String) play.get("playType");
        String value = (String) play.get("value");
        JSONArray positions = (JSONArray) play.get("position");
        String result = playString + playType;
        result += " " + String.valueOf(positions.get(0));
        result += " " + String.valueOf(positions.get(1));
        result += " " + value + "\n";

        return result;
    }

    @Test
    public void autoPlayingKakuroToWin() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro/plays/output.json";
        String kakuroFolder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro";

        assertTrue(autoPlayGame(playsToWin, playsOutput, kakuroFolder));
    }

    @Test
    public void autoPlayingSudokuToWin() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku/plays/output.json";
        String sudokuFolder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku";

        assertTrue(autoPlayGame(playsToWin, playsOutput, sudokuFolder));
    }

    @Test
    public void autoPlayingInohiToWin() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/output.json";
        String inohiFolder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi";

        assertTrue(autoPlayGame(playsToWin, playsOutput, inohiFolder));
    }

    @Test
    public void autoPlayingInohiToWin2() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi2/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi2/plays/output.json";
        String inohiFolder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi2";

        assertTrue(autoPlayGame(playsToWin, playsOutput, inohiFolder));
    }

    @Test
    public void autoPlayingInohiWithUndoAndStop() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/plays_with_undo.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi";
        String expectedOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/output_plays_with_undo_expected.json";

        autoPlayGame(playsToWin, playsOutput, folder);

        assertTrue(checkPlaysFileContent(playsOutput, expectedOutput));
    }

    @Test
    public void autoPlayingInohiWithUndoToWin() {
        String plays = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/plays_with_undo.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays/output.json";
        String inohiFolder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi";

        assertFalse(autoPlayGame(plays, playsOutput, inohiFolder));
    }

    @Test
    public void autoPlayingCountryRoadToWin() {
        Graph.reset();
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/country_road_2/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/country_road_2/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/country_road_2";

        assertTrue(autoPlayGame(playsToWin, playsOutput, folder));
    }

    @Test
    public void autoPlayingSlitherlinkToWin() {
        Graph.reset();
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/slitherlink/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/slitherlink/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/slitherlink";

        assertTrue(autoPlayGame(playsToWin, playsOutput, folder));
    }

    @Test
    public void autoPlayingGokigenToWin() {
        Graph.reset();
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/gokigen/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/gokigen/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/gokigen";

        assertTrue(autoPlayGame(playsToWin, playsOutput, folder));
    }

    @Test
    public void autoPlayingPicAPixToWin() {
        Graph.reset();
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/pic_a_pix/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/pic_a_pix/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/pic_a_pix";

        assertTrue(autoPlayGame(playsToWin, playsOutput, folder));
    }

    @Test
    public void autoPlayingKitanoriToWin() {
        Graph.reset();
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kitanori/plays/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kitanori/plays/output.json";
        String folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kitanori";

        assertTrue(autoPlayGame(playsToWin, playsOutput, folder));
    }

    /*
     * Return true if file json content is the same
     *
     */
    static boolean checkPlaysFileContent(String filePath, String filePathExpected) {
        JSONArray outputJson;
        JSONArray outputExpectedJson;
        try {
            JSONParser parser = new JSONParser();
            outputJson = (JSONArray) parser.parse(new FileReader(filePath));
            outputExpectedJson = (JSONArray) parser.parse(new FileReader(filePathExpected));
        } catch (ParseException e) {
            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

        if (outputJson.toString().equals(outputExpectedJson.toString())) {
            return true;
        }
        return false;
    }
}
