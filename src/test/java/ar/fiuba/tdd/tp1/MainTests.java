package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.utilities.GameParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertTrue;

public class MainTests {

    boolean autoPlayGame(String playsInput,
                         String playsOutput,
                         String gameFolderPath) {
        try {
            // LEEMOS DE ARCHIVO Y CARGAMOS LAS JUGADAS
            JSONParser jsonParser = new JSONParser();
            String playString = "";
            JSONObject jsonObject  = (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(playsInput), "UTF-8"));
            JSONArray plays = (JSONArray) jsonObject.get("plays");
            for (int i = 0; i < plays.size(); ++i) {
                playString = parsePlayString((JSONObject) plays.get(i), playString);
            }
            System.out.println(playString);
            InputStream stream = new ByteArrayInputStream(playString.getBytes(StandardCharsets.UTF_8));
            System.setIn(stream);

            GameParser parser = new GameParser(gameFolderPath);
            parser.parseGameStructure();
            parser.parseGameRules();

            Game game = parser.getGame();
            GameBoardController controller = new GameLoop(game, playsOutput);

            controller.start();

            return game.checkRules();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String kakuro_folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro";

        assertTrue(autoPlayGame(playsToWin, playsOutput, kakuro_folder));
    }

    @Test
    public void autoPlayingSudokuToWin() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String sudoku_folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku";

        assertTrue(autoPlayGame(playsToWin, playsOutput, sudoku_folder));
    }

    @Test
    public void autoPlayingInohiToWin() {
        String playsToWin = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi/plays_to_win.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String inohi_folder = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi";

        assertTrue(autoPlayGame(playsToWin, playsOutput, inohi_folder));
    }

}
