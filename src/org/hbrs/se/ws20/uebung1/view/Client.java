package org.hbrs.se.ws20.uebung1.view;

import org.hbrs.se.ws20.uebung1.control.Factory;
import org.hbrs.se.ws20.uebung1.control.Translator;


public class Client{

	/*
	 * Methode zur Ausgabe einer Zahl auf der Console
	 */
	public static void display( int aNumber ){
		// In dieser Methode soll die Methode translateNumber 
		// mit dem übergegebenen Wert der Variable aNumber 
		// aufgerufen werden.
		// Strenge Implementierung gegen das Interface Translator gewuenscht!

		Translator translator = Factory.createGermanTranslator();
		String result = translator.translateNumber(aNumber);
		System.out.println("Das Ergebnis der Berechnung: "+ result );
	}
}




