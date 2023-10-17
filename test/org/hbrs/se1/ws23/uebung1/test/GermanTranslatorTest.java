package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.imageio.plugins.bmp.BMPImageWriteParam;
import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    /**
     * Black-Box Test durchführen
     * Ist-Wert -> Black-Box -> Output
     * Output --> IST-Wert = SOLL-Wert
     */

    @Test
    void aPositiveTestNr1() {
        Translator translator = new GermanTranslator();
        String value = translator.translateNumber(1);
        assertEquals(value, "eins");
    }

    @Test
    void aPositiveTestNr2() {
        Translator translator = new GermanTranslator();
        String value = translator.translateNumber(10);
        assertEquals(value, "zehn");
    }

    @Test
    void aNegativeTestNr1() {
        Translator translator = new GermanTranslator();
        assertThrows(InputMismatchException.class, () -> translator.translateNumber(0));
    }

    @Test
    void aNegativeTestNr2() {
        Translator translator = new GermanTranslator();
        assertThrows(InputMismatchException.class, () -> translator.translateNumber(11));
    }
}