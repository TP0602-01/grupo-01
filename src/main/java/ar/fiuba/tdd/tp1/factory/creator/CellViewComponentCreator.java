package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.view.draw.cellcomponents.BorderView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CornerView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.DataView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;


public enum CellViewComponentCreator {

    DATA_VIEW_CREATOR("data") {
        @Override
        public CellViewComponent createCellViewComponent(ArrayList<String> values) {
            return new DataView();
        }
    },

    CORNER_VIEW_CREATOR("corner") {
        @Override
        public CellViewComponent createCellViewComponent(ArrayList<String> values) {


            int offsetX = Integer.parseInt(values.get(0));
            int offsetY = Integer.parseInt(values.get(1));
            return new CornerView(new Point(offsetX, offsetY), values.get(2));
        }
    },


    BORDER_COMPONENT_CREATOR("border") {
        @Override
        public CellViewComponent createCellViewComponent(ArrayList<String> values) {
            return new BorderView();
        }
    };


    public final String stringRepresentation;

    CellViewComponentCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public abstract CellViewComponent createCellViewComponent(ArrayList<String> values);

}
