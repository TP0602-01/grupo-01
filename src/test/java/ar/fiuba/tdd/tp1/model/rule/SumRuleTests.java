package ar.fiuba.tdd.tp1.model.rule;


import ar.fiuba.tdd.tp1.cell.Cell;
import ar.fiuba.tdd.tp1.cell.InputCell;
import ar.fiuba.tdd.tp1.rule.SumRule;
import ar.fiuba.tdd.tp1.walk.Walk;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class SumRuleTests {

    private static final int INITIAL_ROW = 0;
    private static final int INITIAL_COLUMN = 0;
    SumRule sumRule;


    private Walk createAWalkMock(int initialRow, int initialColumn, String[][] results){
        Walk walkMock = mock(Walk.class);
        Vector<Cell> collectionReturnedByWalk = new Vector<>();
        for(int row = 0; row < results.length ; row++){
            for (int column = 0 ; column < results[row].length; column++)
                collectionReturnedByWalk.add(new InputCell(results[row][column]));
        }
        when(walkMock.getCellList(initialRow,initialColumn)).thenReturn(collectionReturnedByWalk);
        return walkMock;
    }


    @Test
    public void checkingTheSumOfTwoCells(){
        String[][] results = {{"1" ,"2"}};
        Walk walkMock = createAWalkMock(0,0,results);
        Collection<String> cellAsString = new ArrayList<>();
        cellAsString.add("0,0");
        sumRule = new SumRule(cellAsString,walkMock,3);
        assertTrue(sumRule.check());
    }



}
