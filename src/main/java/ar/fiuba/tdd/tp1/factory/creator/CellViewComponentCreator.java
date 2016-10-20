package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.view.draw.cellcomponents.BorderView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CornerView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.DataView;

import java.awt.*;
import java.util.Collection;


public enum CellViewComponentCreator {

    DATA_VIEW_CREATOR("data") {
        @Override
        public CellViewComponent createCellViewComponent(Collection<String> values) {
            return new DataView();
        }
    },

    CORNER_VIEW_CREATOR("corner") {
        @Override
        public CellViewComponent createCellViewComponent(Collection<String> values) {
            String[] valuesArray = (String[]) values.toArray();
            int xOffset = Integer.parseInt(valuesArray[0]);
            int yOffset = Integer.parseInt(valuesArray[1]);
            return new CornerView(new Point(xOffset,yOffset),valuesArray[2]);
        }
    },


    BORDER_COMPONENT_CREATOR("border") {
        @Override
        public CellViewComponent createCellViewComponent(Collection<String> values) {
            return new BorderView();
        }
    };


    public final String stringRepresentation;

    CellViewComponentCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public abstract CellViewComponent createCellViewComponent(Collection<String> values);

}
