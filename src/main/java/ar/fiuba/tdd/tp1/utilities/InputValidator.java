package ar.fiuba.tdd.tp1.utilities;


import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class InputValidator {

    //private final String possibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/input.txt";
    //private final String possibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/country_road_input.txt";
    //private final String possibleInputsFile = "./src/main/java/ar/fiuba/tdd/tp1/game_files/gokigen_naname_input.txt";
    private Set<String> possibleInputs = new HashSet<>();

    public InputValidator(String possibleInputFileName) {
        loadPossibleInputsFromFile(possibleInputFileName);
    }

    private void loadPossibleInputsFromFile(String possibleInputFiles) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(possibleInputFiles), "UTF-8"));
            String currentLine;
            while ((currentLine = input.readLine()) != null) {
                possibleInputs.add(currentLine);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAValidInput(String input) {
        return possibleInputs.contains(input);
    }

}
