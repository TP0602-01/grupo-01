package ar.fiuba.tdd.tp1.utilities;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.factory.CellViewComponentFactory;
import ar.fiuba.tdd.tp1.gameboard.GameBoard;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.view.BoardView;
import ar.fiuba.tdd.tp1.view.draw.CellView;
import ar.fiuba.tdd.tp1.view.draw.RegionView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ViewParser extends Parser {

    private JSONParser parser = new JSONParser();
    private JSONObject fileJsonRepresentation;

    public BoardView getBoardView() {
        return boardView;
    }

    private BoardView boardView;
    private String filePath;


    public ViewParser(String file, GameBoard gameBoard) {
        this.filePath = file;
        this.gameBoard = gameBoard;
        boardView = new BoardView(gameBoard);
        try {
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(file + "/view.json"), "UTF-8");
            fileJsonRepresentation = (JSONObject) parser.parse(fileReader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void parseViewObjects() {

        JSONArray singleCellViews = (JSONArray) fileJsonRepresentation.get("singleCellViews");
        Iterator iterator = singleCellViews.iterator();

        while (iterator.hasNext()) {
            JSONObject singleCellView = (JSONObject) iterator.next();
            int coordX = Integer.parseInt((String) singleCellView.get("xCoord"));
            int coordY = Integer.parseInt((String) singleCellView.get("yCoord") );
            Collection<CellView> cellViews = new ArrayList<>();
            cellViews.add(boardView.getCellView(coordX, coordY));
            this.parseComponents(singleCellView, cellViews);
        }


        JSONObject allCellViewsJson = (JSONObject) fileJsonRepresentation.get("allCellViews");
        Collection<CellView> allCellViews = new ArrayList<>();
        for (int i = 0; i < boardView.getWidth(); i++) {
            for (int j = 0; j < boardView.getHeight(); j++) {
                allCellViews.add(boardView.getCellView(i, j));
            }
        }
        this.parseComponents(allCellViewsJson, allCellViews);


        boolean drawLinks = (boolean) fileJsonRepresentation.get("visibleLinks");

        boardView.initializeLinkViews(drawLinks,Graph.getSingleInstance());

        parseGroups();

    }


    private void parseGroups() {
        String groupFilePath = filePath + "/view_sets.json";

        try {
            InputStreamReader fileReader = new InputStreamReader(new FileInputStream(groupFilePath), "UTF-8");
            JSONObject sets = (JSONObject) parser.parse(fileReader);
            JSONArray setsArray = (JSONArray) sets.get("sets");
            Iterator setIterator = setsArray.iterator();

            while (setIterator.hasNext()) {
                JSONArray set = (JSONArray) ((JSONObject) setIterator.next()).get("set");

                ArrayList<Cell> cells = getSetCells(set);

                boardView.addDrawable(new RegionView(cells,3));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void parseComponents(JSONObject json, Collection<CellView> cellViews) {

        JSONArray components = (JSONArray) json.get("components");

        Iterator<JSONObject> iterator = components.iterator();
        String type;

        while (iterator.hasNext()) {
            ArrayList<String> values = new ArrayList<>();
            JSONObject component = iterator.next();
            type = (String) component.get("type");
            JSONArray jsonValues = (JSONArray) component.get("values");
            Iterator<JSONObject> iter = jsonValues.iterator();

            while (iter.hasNext()) {
                JSONObject value = iter.next();
                values.add((String) value.get("value"));
            }

            for (CellView cellView : cellViews) {
                CellViewComponent cellViewComponent = CellViewComponentFactory.create(type, values);
                cellView.addComponent(cellViewComponent);
            }

        }
    }




}
