package ar.fiuba.tdd.tp1.utilities;


import ar.fiuba.tdd.tp1.factory.CellViewComponentFactory;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.draw.CellView;
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

    public void parseComponents(JSONObject json, Collection<CellView> cellViews) {

        JSONArray components = (JSONArray) json.get("components");

        Iterator<JSONObject> iterator = components.iterator();
        String type;

        while ( iterator.hasNext() ) {
            Collection<String> values = new ArrayList<>();
            JSONObject component = iterator.next();
            type = (String) component.get("type");
            JSONArray jsonValues = (JSONArray) component.get("values");
            Iterator<JSONObject>  iter = jsonValues.iterator();

            while ( iter.hasNext() ) {
                JSONObject value = iter.next();
                values.add((String) value.get("value"));
            }

            for (CellView cellView: cellViews
                 ) {
                CellViewComponent cellViewComponent = CellViewComponentFactory.create(type,values);
                cellView.addComponent(cellViewComponent);
            }



        }
    }



    public void parseViewObjects() {

        JSONObject allCellViewsJson = (JSONObject) fileJsonRepresentation.get("allCellViews");

        Collection<CellView> allCellViews = new ArrayList<>();
        for ( int i = 0 ; i < boardView.getWidth() ; i ++ ) {
            for ( int j = 0 ; j < boardView.getHeight() ; j ++) {
                allCellViews.add(boardView.getCellView(i,j));
            }
        }

        this.parseComponents(allCellViewsJson,allCellViews);




        JSONArray singleCellViews = (JSONArray) fileJsonRepresentation.get("singleCellViews");


        Iterator<JSONObject> iterator = singleCellViews.iterator();
        while ( iterator.hasNext() ) {

            JSONObject singleCellView = iterator.next();
            int xCoord = (int) singleCellView.get("xCoord");
            int yCoord = (int) singleCellView.get("yCoord");

            Collection<CellView> cellViews = new ArrayList<>();

            cellViews.add(boardView.getCellView(xCoord,yCoord));

            this.parseComponents(allCellViewsJson,allCellViews);

        }


    }

}
