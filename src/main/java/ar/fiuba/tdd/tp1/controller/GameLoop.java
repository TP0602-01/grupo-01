package ar.fiuba.tdd.tp1.controller;

import ar.fiuba.tdd.tp1.game.Game;
import org.json.simple.JSONArray;

import java.io.*;

/**
 * GameLoop reads the user plays and put then into the game.
 * When the User makes a play, the game status is showed and
 * when the game is over, all plays are save in a file.
 */
public class GameLoop implements GameBoardController {

    private Game game;
    private String outputPlayFile;

    private GamePlay lastPlay;

    public GameLoop(Game game, String outputPlayFile) {
        this.game = game;
        this.outputPlayFile = outputPlayFile;
        lastPlay = new GamePlay();
    }

    /** Start the game.
     *  If user win, inform that with a message and finish the Game
     *  If user make an invalid play, inform that with a message and continue the game
     */
    public void start() throws IOException {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        JSONArray plays = new JSONArray();
        lastPlay.checkRules(game);

        while (!lastPlay.gameOver()) {
            InputCellData data = new InputCellData(console.readLine());
            InputCellAction action = data.generateAction(game, lastPlay);
            action.act();

            /* an action could have finished the game */
            if (lastPlay.gameOver()) {
                break;
            }

            lastPlay.checkRules(game);
            plays.add(lastPlay.serialize());
            lastPlay.increaseNumber();

            System.out.println("Estado del tablero: " + lastPlay.gameOver());
        }
        /* when the game has finished, we save plays in a file */
        this.savePlays(plays.toJSONString(), outputPlayFile);
    }


    /** Save plays in File.
     *
     */
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
