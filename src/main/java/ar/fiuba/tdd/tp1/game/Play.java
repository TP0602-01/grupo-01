package ar.fiuba.tdd.tp1.game;

/**
 * User Play.
 * Have x and y position and the Cell Content.
 */
public class Play {
    private int rowPosition;
    private int columPosition;
    private String content;

    public Play(int rowPosition, int columPosition, String content) {
        this.rowPosition = rowPosition;
        this.columPosition = columPosition;
        this.content = content;
    }

    public int getRowPosition() {
        return rowPosition;
    }

    public int getColumPosition() {
        return columPosition;
    }

    public String getContent() {
        return content;
    }
}
