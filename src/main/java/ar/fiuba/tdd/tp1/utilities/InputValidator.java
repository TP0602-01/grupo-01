package ar.fiuba.tdd.tp1.utilities;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class InputValidator {

    //private final String posibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/input.txt";
    private final String posibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/country_road_input.txt";
    private Set<String> posibleInputs = new HashSet<>();

    public InputValidator() throws Exception {
        loadPosibleInputsFromFile(posibleInputsFile);
    }

    private void loadPosibleInputsFromFile(String posibleInputFiles) throws Exception {
        BufferedReader input = new BufferedReader(new FileReader(posibleInputFiles));
        String currentLine;
        while ((currentLine = input.readLine()) != null) {
            posibleInputs.add(currentLine);
        }
    }

    public boolean isAValidInput(String input) {
        return posibleInputs.contains(input);
    }


}
