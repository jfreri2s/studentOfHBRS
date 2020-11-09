package org.hbrs.se.ws20.uebung1.control;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    GermanTranslator translator;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        translator = new GermanTranslator();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        translator = null;
    }

    @Test
    void translateNumber() {
        assertEquals(translator.translateNumber(-5),"Uebersetzung der Zahl " + -5 + " nicht möglich " + Translator.version);
        assertEquals(translator.translateNumber(-1),"Uebersetzung der Zahl " + -1 + " nicht möglich " + Translator.version);
        assertEquals(translator.translateNumber(0),"Uebersetzung der Zahl " + 0 + " nicht möglich " + Translator.version);
        assertEquals(translator.translateNumber(1),"eins");
        assertEquals(translator.translateNumber(10),"zehn");
        assertEquals(translator.translateNumber(15), "Uebersetzung der Zahl " + 15 + " nicht möglich " + Translator.version);
    }
}