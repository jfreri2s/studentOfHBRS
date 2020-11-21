package org.hbrs.se.ws20.solutions.uebung1.control.test;

import org.hbrs.se.ws20.solutions.uebung1.view.Client;

public class BlackBoxTest {
	
	private Client client = null;

	public BlackBoxTest() {
		client = new Client();
	}
	
	/*
	 * Die Klasse BlackBoxTest dient hier auch als Test-Klasse, um die Klasse
	 * Processor zu testen. Loesung eher suboptimal, da man das Ergebnis ablesen
	 * muss, eine automatisierte Ueberpruefung ist nicht moeglich
	 * Verbesserung: Methode execute sollte String-Wert zurueckliefern (Refactoring!)
	 */
	public void test(){ 
		
		// Testfaelle fuer Positivtests (hier vollstaendig, ein hinreichender Test mit 
		// einem repraesentativen Wert aus der pos_AK (z.B. 4) waere ok!
		client.display( 1 ); 
		client.display( 2 );
		client.display( 3 );
		client.display( 4 );
		client.display( 5 );
		client.display( 6 );
		client.display( 7 );
		client.display( 8 );
		client.display( 9 );
		client.display( 10 );
		
		
		// Testfaelle fuer Negativtests 
		client.display( 11 );
		client.display( -1 );
		client.display( 0 ); 
		
		// Umlenkung der Ausgabe auf einen Englischen Translator:
		// this.client.setTranslator( new EnglishTranslator() );
		
		// Positivtest (man achte auf die veraenderte Ausgabe!!)
		client.display( 1 );
		
		// Negativtest (man achte auf die veraenderte Ausgabe!!)
		client.display( 11 );
		
	}

	
	public static void main(String[] args) {
		BlackBoxTest ass = new BlackBoxTest();
		ass.test();
	}

}
