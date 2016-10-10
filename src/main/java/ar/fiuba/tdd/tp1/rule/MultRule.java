package ar.fiuba.tdd.tp1.rule;

import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.set.Graph;

import java.util.Collection;

/*  */
public class MultRule extends Rule {

    private int expectedMultResult;

    public MultRule(Integer multResult) {
        this.expectedMultResult = multResult;
    }

    @Override
    public boolean check(Graph graph) { //TODO: ESTO TIENE TODA LA PINTA DE UN TEMPLATE METHOD
        Collection<Cell> cells = graph.getCells();

        Integer mult = 1;   //TODO: A NADIE SE LE OCURRA PONER 0!!
        for (Cell cell : cells) {
            mult *= Integer.parseInt(cell.getData());
        }

        return mult.equals(expectedMultResult);
    }
}
