package ar.fiuba.tdd.tp1.utilities;


import java.io.*;
import java.util.HashSet;
import java.util.Set;

/*
 * Input validator receive an posible plays input file and
 * check that the input is valid (is contained into this plays file)
 *
 */
public class InputValidator {

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
