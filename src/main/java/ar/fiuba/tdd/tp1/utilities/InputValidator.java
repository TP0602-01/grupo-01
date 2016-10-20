package ar.fiuba.tdd.tp1.utilities;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InputValidator {

    //private final String posibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/input.txt";
    //private final String posibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/country_road_input.txt";
    //private final String posibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/gokigen_naname_input.txt";
    private Set<String> posibleInputs = new HashSet<>();

    public InputValidator(String possibleInputFileName) {
        loadPosibleInputsFromFile(possibleInputFileName);
    }

    private void loadPosibleInputsFromFile(String posibleInputFiles) {
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(posibleInputFiles));
            String currentLine;
            while ((currentLine = input.readLine()) != null) {
                posibleInputs.add(currentLine);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public boolean isAValidInput(String input) {
        return posibleInputs.contains(input);
    }

}
