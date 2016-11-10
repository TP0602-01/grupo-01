package ar.fiuba.tdd.tp1.graph.linker;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Each entry of this table is structured like this:
 * |rowOffset|columnOffset|originToken|destinationToken
 * Each entry shows if the originToken can be linked to the destinationToken
 * for a given row+coll offset.
 */
public class LinkingTable {

    private Map<Pair<Integer, Integer>, Map<String, Set<String>>> table;


    public LinkingTable() {
        this.table = new HashMap<>();
    }


    private Map<String, Set<String>> getLinkiableTokenInOffset(int rowOffset, int columnOffset) {
        Pair<Integer, Integer> directionOffset = new Pair<>(rowOffset, columnOffset);
        Map<String, Set<String>> linkeableTokensInChosenOffset;
        if (this.table.containsKey(directionOffset)) {
            linkeableTokensInChosenOffset = this.table.get(directionOffset);
        } else {
            linkeableTokensInChosenOffset = new HashMap<>();
        }
        return linkeableTokensInChosenOffset;
    }

    private Set<String> getLinkeableTokensForOrigin(String originToken, Map<String, Set<String>> linkeableTokensInOffset) {
        Set<String> destinationTokensInOffset;
        if (linkeableTokensInOffset.containsKey(originToken)) {
            destinationTokensInOffset = linkeableTokensInOffset.get(originToken);
        } else {
            destinationTokensInOffset = new HashSet<>();
        }
        return destinationTokensInOffset;
    }

    private void addToTable(int rowOffset, int columnOffset, Map<String, Set<String>> linkeableTokensInOffset) {
        Pair<Integer, Integer> directionOffset = new Pair<>(rowOffset, columnOffset);
        this.table.put(directionOffset, linkeableTokensInOffset);
    }


    public void addEntry(int rowOffset, int columnOffset, String originToken, String destinationToken) {
        Map<String, Set<String>> linkeableTokensInOffset = this.getLinkiableTokenInOffset(rowOffset, columnOffset);
        Set<String> destinationTokensInOffset = this.getLinkeableTokensForOrigin(originToken, linkeableTokensInOffset);
        destinationTokensInOffset.add(destinationToken);
        linkeableTokensInOffset.put(originToken, destinationTokensInOffset);
        this.addToTable(rowOffset, columnOffset, linkeableTokensInOffset);
    }

    public boolean checkEntryExistance(int rowOffset, int columnOffset, String originToken, String destinationToken) {
        Pair<Integer, Integer> offset = new Pair<>(rowOffset, columnOffset);

        if (this.table.containsKey(offset)) {
            Map<String, Set<String>> originTokensInChosenOffset = this.table.get(offset);
            if (originTokensInChosenOffset.containsKey(originToken)) {
                Set<String> destinationTokensInChosenOffset = originTokensInChosenOffset.get(originToken);
                return destinationTokensInChosenOffset.contains(destinationToken);
            }
        }
        return false;
    }

    Set<Pair<Integer, Integer>> getOffsets() {
        return this.table.keySet();
    }
}
