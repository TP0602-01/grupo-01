package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.view.draw.cellcomponents.BorderView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.CellViewComponent;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.DataView;
import ar.fiuba.tdd.tp1.view.draw.cellcomponents.VariablePositionFixedData;


import java.util.ArrayList;


/*
 * Cell View creator represent a Factory Method that can create
 * different cell view types.
 *
 */
public enum CellViewComponentCreator {

    DATA_VIEW_CREATOR("data") {
        @Override
        public CellViewComponent createCellViewComponent(ArrayList<String> values) {
            return new DataView();
        }
    },

    VARIABLE_POSITION_FIXED_DATA_CREATOR("variablePositionFixedData") {
        @Override
        public CellViewComponent createCellViewComponent(ArrayList<String> values) {

            double offsetX = Double.parseDouble(values.get(0));
            double offsetY = Double.parseDouble(values.get(1));
            return new VariablePositionFixedData(offsetX,offsetY, values.get(2));
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
