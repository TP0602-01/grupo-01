package ar.fiuba.tdd.tp1;

import ar.fiuba.tdd.tp1.controller.GameBoardController;
import ar.fiuba.tdd.tp1.controller.GameLoop;
import ar.fiuba.tdd.tp1.game.Game;
import ar.fiuba.tdd.tp1.utilities.GameParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static junit.framework.TestCase.assertTrue;

public class MainTests {


    boolean autoPlayGame(String plays_input,
                         String structure,
                         String sets,
                         String link_symbols,
                         String link_table,
                         String plays_output,
                         String possible_input) {
        try {
            // LEEMOS DE ARCHIVO Y CARGAMOS LAS JUGADAS
            JSONParser jsonParser = new JSONParser();
            String playString = "";
            try {
                JSONObject jsonObject  = (JSONObject) jsonParser.parse(new FileReader(plays_input));
                JSONArray plays = (JSONArray) jsonObject.get("plays");
                for(int i = 0; i < plays.size(); i++) {
                    JSONObject play = (JSONObject) plays.get(i);
                    String value = (String) play.get("value");
                    JSONArray positions = (JSONArray) play.get("position");

                    playString += String.valueOf(positions.get(0)) + " " +
                                        String.valueOf(positions.get(1)) + " " +
                                        value + "\n";
                }
                System.out.println(playString);
                InputStream stream = new ByteArrayInputStream(playString.getBytes(StandardCharsets.UTF_8));
                System.setIn(stream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GameParser parser = new GameParser(structure, sets, link_symbols, link_table, possible_input);
            parser.parseGameStructure();
            parser.parseGameRules();

            Game game = parser.getGame();
            GameBoardController controller = new GameLoop(game, plays_output);

            controller.start();

            if (game.checkRules()) {
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Test
    public void autoPlayingKakuroToWin() {
        String plays_to_win = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro_plays_to_win.json";
        String structure = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro_structure.json";
        String sets = "./src/test/java/ar/fiuba/tdd/tp1/test_files/kakuro_sets.json";
        String link_symbols = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_symbols.json";
        String link_table = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_table.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String possibleInput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/possible_input_kakoru.txt";

        assertTrue(autoPlayGame(plays_to_win, structure, sets, link_symbols, link_table, playsOutput, possibleInput));
    }

    @Test
    public void autoPlayingSudokuToWin() {
        String plays_to_win = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku_plays_to_win.json";
        String structure = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku_structure.json";
        String sets = "./src/test/java/ar/fiuba/tdd/tp1/test_files/sudoku_sets.json";
        String link_symbols = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_symbols.json";
        String link_table = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_table.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String possibleInput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/possible_input_sudoku.txt";

        assertTrue(autoPlayGame(plays_to_win, structure, sets, link_symbols, link_table, playsOutput, possibleInput));
    }

    @Test
    public void autoPlayingInohiToWin() {
        String plays_to_win = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi_plays_to_win.json";
        String structure = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi_structure.json";
        String sets = "./src/test/java/ar/fiuba/tdd/tp1/test_files/inohi_sets.json";
        String link_symbols = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_symbols.json";
        String link_table = "./src/test/java/ar/fiuba/tdd/tp1/test_files/empty_linking_table.json";
        String playsOutput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/output.json";
        String possibleInput = "./src/test/java/ar/fiuba/tdd/tp1/test_files/possible_input_inohi.txt";

        assertTrue(autoPlayGame(plays_to_win, structure, sets, link_symbols, link_table, playsOutput, possibleInput));
    }

}
