package org.hbrs.se.ws20.uebung1.control;

import org.hbrs.se.ws20.uebung1.control.GermanTranslator;

public class Factory {
    public static GermanTranslator createGermanTranslator(){
        return new GermanTranslator();
    }
}