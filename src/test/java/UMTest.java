import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UMTest {
    UM um;
    Integer a = 1;
    Integer b = 2;
    Integer c = 3;

    @BeforeEach
    void setUp() {
        um = new UM();
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
    void arrayIndexShouldCopyOffsetCOfArrayBtoRegisterA() {
        Integer expected = 156;
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        um.set(c, 4);
        um.setArray(b, 5, 6, 7, 8, expected);

        um.exec(1, a, b, c);

        assertEquals(expected, um.get(a));
    }

    @Test
    void arrayAmendmentShouldStoreRegisterCInOffsetBofArrayA() {
        Integer expected = 76;
        um.set(b, 4);
        um.set(c, expected);
        um.setArray(a, 0,0,0,0,0);

        um.exec(2, a, b, c);

        assertEquals(expected, um.getArray(a, um.get(b)));
    }

    @Test
    void additionShouldStoreSumOfRegistersBandCinA() {
        int valB = 4;
        int valC = 5;

        um.set(b, valB);
        um.set(c, valC);
        um.exec(3, a, b, c);

        assertEquals(valB+valC, um.get(a));
    }

    @Test
    void additionShouldStoreSumOfRegistersBandCMod2ToThe32inA() {
        Integer valB = 0xffffffff;
        Integer valC = 0x00000001;

        um.set(b, valB);
        um.set(c, valC);
        um.exec(3, a, b, c);

        assertEquals(0, um.get(a));
    }

    @Test
    void multiplicationsShouldStoreProductOfRegistersBandCMod2ToThe32InA() {
        Integer valB = 0xffffffff;
        Integer valC = 0x00000010;

        um.set(b, valB);
        um.set(c, valC);
        um.exec(4, a, b, c);

        assertEquals(0xfffffff0, um.get(a));
    }

    @Test
    void divisionShouldStoreQuotientOfRegistersBandCInA() {
        Integer valB = 5;
        Integer valC = 2;

        um.set(b, valB);
        um.set(c, valC);
        um.exec(5, a, b, c);

        assertEquals(2, um.get(a));
    }

    @Test
    void notAndShouldStoreNandOfRegistersBandCInA() {
        Integer valB = 0xffff0000;
        Integer valC = 0x00ff00ff;

        um.set(b, valB);
        um.set(c, valC);
        um.exec(6, a, b, c);

        assertEquals(0xff00ffff,
                um.get(a),
                "Got " + Integer.toBinaryString(um.get(a)));
    }
}
