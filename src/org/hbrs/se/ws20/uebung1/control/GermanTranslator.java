package org.hbrs.se.ws20.uebung1.control;

import java.util.Map;

public class GermanTranslator implements Translator {
	public String date = "Okt/2020"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) {
		Map<Integer,String> map = Map.of(1,"eins",2,"zwei",3,"drei",4,"vier",5,"fünf",6,"sechs",7,"sieben",8,"acht",9,"neun",10,"zehn");
		return map.getOrDefault(number,"Uebersetzung der Zahl " + number + " nicht möglich " + "(" + Translator.version + ")");
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo() {
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: Nov/2020))
	 * Das Datum sollte system-intern gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}