package ar.fiuba.tdd.tp1.utilities;


import ar.fiuba.tdd.tp1.factory.CellViewComponentFactory;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ViewParser {

    private JSONParser parser = new JSONParser();
    private JSONObject fileJsonRepresentation;
    private BoardView boardView;

    public ViewParser(String file, BoardView boardView) {
        try {
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            fileJsonRepresentation = (JSONObject) parser.parse(fileReader);
            this.boardView = boardView;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void parseViewObjects() {

        JSONObject allCellViews = (JSONObject) fileJsonRepresentation.get("allCellViews");
        JSONArray components = (JSONArray) allCellViews.get("components");

        Iterator<JSONObject> iterator = components.iterator();
        String type;
        Collection<String> values = new ArrayList<>();

        while ( iterator.hasNext() ) {
            JSONObject component = iterator.next();
            type = (String) component.get("type");
            JSONArray jsonValues = (JSONArray) component.get("values");
            Iterator<JSONObject>  iter = jsonValues.iterator();

            while ( iter.hasNext() ) {
                JSONObject value = iter.next();
                values.add((String) value.get("value"));
            }

            CellViewComponent cellViewComponent = CellViewComponentFactory.create(type,values);
            for ( int i = 0 ; i < boardView.getWidth() ; i ++ ) {
                for ( int j = 0 ; j < boardView.getHeight() ; j ++) {
                    boardView.addCellComponent(i,j,cellViewComponent);
                }
            }
        }



    }

}
