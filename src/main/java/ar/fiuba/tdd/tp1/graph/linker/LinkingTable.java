package ar.fiuba.tdd.tp1.graph.linker;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* */
public class LinkingTable {

    private Map<Pair<Integer, Integer>, Map<String, Set<String>>> table;

    public LinkingTable() {
        this.table = new HashMap<>();
    }

    public void addEntry(int rowOffset, int columnOffset, String originToken, String destinationToken) {
        //Setea en que direccion (representados como offsets) se van a chequear los linkeos
        Pair<Integer, Integer> directionOffset = new Pair<>(rowOffset, columnOffset);

        //TODO: extraer estas verificaciones a metodos
        Map<String, Set<String>> linkeableTokensInChosenOffset;
        if (this.table.containsKey(directionOffset)) {
            linkeableTokensInChosenOffset = this.table.get(directionOffset);
        } else {
            linkeableTokensInChosenOffset = new HashMap<>();
        }

        Set<String> destinationTokensInChosenOffset;
        if (linkeableTokensInChosenOffset.containsKey(originToken)) {
            destinationTokensInChosenOffset = linkeableTokensInChosenOffset.get(originToken);
        } else {
            destinationTokensInChosenOffset = new HashSet<>();
        }

        destinationTokensInChosenOffset.add(destinationToken);
        linkeableTokensInChosenOffset.put(originToken, destinationTokensInChosenOffset);
        this.table.put(directionOffset, linkeableTokensInChosenOffset);
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
