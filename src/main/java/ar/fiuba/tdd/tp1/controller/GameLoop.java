package ar.fiuba.tdd.tp1.controller;


import ar.fiuba.tdd.tp1.game.Game;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

/* */
public class GameLoop implements GameBoardController {

    private Game game;
    private String outputPlayFile;

    public GameLoop(Game game, String outputPlayFile) {
        this.game = game;
        this.outputPlayFile = outputPlayFile;
    }

    /* Start the game.
     * If user win, inform that with a message and finish the Game
     * If user make an invalid play, inform that with a message and continue the game */
    public void start() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        JSONArray plays = new JSONArray();
        boolean gameStatus = game.checkRules();
        boolean playStatus = true;
        int plays_count = 0;

        while (!gameStatus) {
            InputCellData data = new InputCellData(console.readLine());

            if (data.getIndexI() == -1) {   //FINISH THE GAME AND SAVE PLAYS IN FILE
                this.savePlays(plays.toJSONString(), outputPlayFile);
                break;
            }
            playStatus = game.addPlay(data.getIndexI(), data.getIndexJ(), data.getInputData());
            gameStatus = game.checkRules();

            JSONObject play = new JSONObject();
            play.put("number", plays_count);
            play.put("playStatus", playStatus);
            play.put("gameStatus", gameStatus);
            plays.add(play);
            plays_count++;

            System.out.println("Estado del tablero: " + gameStatus);
        }
        // THE GAME HAS FINISHED, SAVE PLAYS IN FILE
        this.savePlays(plays.toJSONString(), outputPlayFile);
    }

    /* User Play */
    private static class InputCellData {
        private int indexI;
        private int indexJ;
        private String data;

        InputCellData(String line) {
            String[] splited = line.split(" ");
            indexI = Integer.parseInt(splited[0]);
            indexJ = Integer.parseInt(splited[1]);
            data = splited[2];
        }

        int getIndexI() {
            return indexI - 1;
        }

        int getIndexJ() {
            return indexJ - 1;
        }

        String getInputData() {
            return data;
        }
    }

    /* Save plays in File */
    private void savePlays(String plays, String fileName) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.write(plays);
        } catch (IOException ex) {

        } finally {
            try {
                writer.close();
            }
            catch (Exception ex) {
                        /*ignore*/
            }
        }
    }

}
