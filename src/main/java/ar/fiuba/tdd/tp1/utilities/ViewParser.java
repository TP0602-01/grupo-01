package ar.fiuba.tdd.tp1.utilities;


import ar.fiuba.tdd.tp1.view.BoardView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ViewParser {

    private JSONParser parser = new JSONParser();
    private JSONObject fileJsonRepresentation;

    public ViewParser(String file, BoardView boardView) {
        try {
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
            fileJsonRepresentation = (JSONObject) parser.parse(fileReader);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    public void parseViewObjects(){
        
    }

}
