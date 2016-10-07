package ar.fiuba.tdd.tp1.factory;

import ar.fiuba.tdd.tp1.cell.Cell;


interface CellCreator {
    Cell createCell(String data);
}
