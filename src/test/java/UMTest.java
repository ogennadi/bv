import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UMTest {
    UM um;
    @BeforeEach
    void setUp() {
        um = new UM();
    }

    @Test
    void conditionalMoveCopiesBtoAifCisNot0() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        um.set(b, 4);
        um.set(c, 5);

        um.exec(0, a, b, c);

        assertEquals(um.get(b), um.get(a));
    }

    @Test
    void conditionalMoveShouldNotCopyIfCIs0() {
        Integer expected = 0;
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        um.set(b, 4);
        um.set(c, 0);

        um.exec(0, a, b, c);

        assertEquals(expected, um.get(a));
    }

    @Test
    void getShouldReturn0ForUnsetRegister() {
        assertEquals(0, um.get(0));
        assertEquals(0, um.get(1));
        assertEquals(0, um.get(2));
        assertEquals(0, um.get(3));
        assertEquals(0, um.get(4));
        assertEquals(0, um.get(5));
        assertEquals(0, um.get(6));
        assertEquals(0, um.get(7));
    }

    @Test
    void getShouldReturnPreviouslySetValue() {
        Integer expected = 5;
        Integer a = 0;

        um.set(a, expected);

        assertEquals(expected, um.get(a) );
    }
}
