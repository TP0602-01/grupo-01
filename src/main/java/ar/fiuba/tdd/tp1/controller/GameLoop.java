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
        int playsCount = 0;

        while (!gameStatus) {
            InputCellData data = new InputCellData(console.readLine());

            if (data.getPlayType().equals("stop")) {  //FINISH THE GAME AND SAVE PLAYS IN FILE
                this.savePlays(plays.toJSONString(), outputPlayFile);
                break;
            } else if (data.getPlayType().equals("undo")) {
                game.undoPlay();
                continue;
            }
            boolean playStatus = game.addPlay(data.getIndexI(), data.getIndexJ(), data.getInputData());
            gameStatus = game.checkRules();

            plays.add( createPlay(playsCount, playStatus, gameStatus) );
            playsCount++;

            System.out.println("Estado del tablero: " + gameStatus);
        }
        // THE GAME HAS FINISHED, SAVE PLAYS IN FILE
        this.savePlays(plays.toJSONString(), outputPlayFile);
    }

    /*
     * Create a Json Object Play and return it.
     *
     */
    private JSONObject createPlay(int playsCount, boolean playStatus, boolean gameStatus) {
        JSONObject play = new JSONObject();
        play.put("number", playsCount);
        play.put("playStatus", playStatus);
        play.put("gameStatus", gameStatus);

        return play;
    }

    /* User Play */
    private static class InputCellData {
        private int indexI;
        private int indexJ;
        private String data;
        private String playType;

        InputCellData(String line) {
            String[] splited = line.split(" ");
            playType = splited[0];
            indexI = Integer.parseInt(splited[1]);
            indexJ = Integer.parseInt(splited[2]);
            data = splited[3];
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

        String getPlayType() {
            return playType;
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
            return;
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
