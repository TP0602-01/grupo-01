//package ar.fiuba.tdd.tp1.factory;
//
//import ar.fiuba.tdd.tp1.cell.Cell;
//import ar.fiuba.tdd.tp1.view.CellView;
//import ar.fiuba.tdd.tp1.view.DataCellView;
//import ar.fiuba.tdd.tp1.view.KakoruCellView;
//import ar.fiuba.tdd.tp1.view.NullCellView;
//
///* */
//public class CellViewFactory {
//
//
//    public static CellView create(Cell cell, String type) {
//        if ((type.equals(Cell.DATA_TYPE)) || type.equals(Cell.HINT_TYPE)) {
//            return new DataCellView(cell);
//        } else if (type.equals(Cell.NULL_TYPE)) {
//            return new NullCellView();
//        } else if (type.equals(Cell.KAKORU_TYPE)) {
//            return new KakoruCellView(cell);
//        } else {
//            return null;
//        }
//    }
//}
