package ar.fiuba.tdd.tp1.view.draw;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.NullCell;
import ar.fiuba.tdd.tp1.graph.Graph;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BoardCanvas extends JPanel {

    private Collection<Drawable> drawables;
    private Map<Cell,Map<Cell,LinkView>> linkViews;
    private Map<Integer, Map<Integer, CellView>> cellViews;

    public BoardCanvas(int width,int height) {
        this.drawables = new ArrayList<>();
        initializeCellViews(width,height);
        this.linkViews = new HashMap<>();
    }

    private void initializeCellViews(int width,int height) {
        Map<Integer, Map<Integer, CellView>> cellViews = new HashMap<>();
        for ( int i = 0; i < width ; i++ ) {
            Map<Integer,CellView> rowSet = new HashMap<>();
            for ( int j = 0 ; j < height ; j++ ) {
                CellView cellView = new CellView(new NullCell());
                rowSet.put(j,cellView);
                drawables.add(cellView);
            }
            cellViews.put(i,rowSet);
        }
        this.cellViews = cellViews;
    }

    public void addLinkView(Cell origin, Cell destination, Graph graph) {
        LinkView newLinkView = new LinkView(origin,destination,graph);

        if ( linkViews.containsValue(origin)) {
            linkViews.get(origin).put(destination,newLinkView);
        } else {
            Map<Cell,LinkView> links = new HashMap<>();
            links.put(destination,newLinkView);
            linkViews.put(origin,links);
        }
        drawables.add(newLinkView);
    }

    private void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }


    public CellView getCellView(int row, int column) {
        return cellViews.get(row).get(column);
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawables.forEach(drawable -> drawable.draw(graphics));

    }


    public void addCellComponent(int row ,int column, CellViewComponent component) {
        this.cellViews.get(row).get(column).addComponent(component);
    }


}
