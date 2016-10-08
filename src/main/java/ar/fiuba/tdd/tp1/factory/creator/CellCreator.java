package ar.fiuba.tdd.tp1.factory.creator;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.FixedCell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.cell.NullCell;


public enum CellCreator {

    KAKORU_CELL_CREATOR("kakoru") {
        @Override
        public Cell createCell(String data) {
            return new FixedCell(data);
        }
    },

    HINT_CELL_CREATOR("hint") {
        @Override
        public Cell createCell(String data) {
            return new FixedCell(data);
        }
    },

    NULL_CELL_CREATOR("nullcell") {
        @Override
        public Cell createCell(String data) {
            return new NullCell();
        }
    },


    DATA_CELL_CREATOR("data") {
        @Override
        public Cell createCell(String data) {
            return new InputCell();
        }
    };


    public String stringRepresentation;

    CellCreator(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public abstract Cell createCell(String data);
}
