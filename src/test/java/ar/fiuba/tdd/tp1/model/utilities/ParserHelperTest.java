package ar.fiuba.tdd.tp1.model.utilities;

import ar.fiuba.tdd.tp1.utilities.ParserHelper;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ParserHelperTest {

    @Test
    public void paserToIntegerMustReturnStringCastedToInt() {
        Integer numberTwo = 2;
        ParserHelper parserHelper = new ParserHelper();

        assertEquals(parserHelper.toInteger("2"), numberTwo);
    }
}
