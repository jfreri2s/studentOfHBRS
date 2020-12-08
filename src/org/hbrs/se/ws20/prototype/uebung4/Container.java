package org.hbrs.se.ws20.prototype.uebung4;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * Erkenntnis aus den Übungen: die Klasse MUSS aufgesplittet werden!
 * (siehe Blueprint einer Architektur auf LEA)
 * 
 * erstellt von Julius P., H-BRS 2020, Version 1.5
 * 
 */

public class Container {
	 
	// Interne ArrayList zur Abspeicherung der Objekte
	private List<UserStory> liste = null; 
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... --> RICHTIG
	private static Container instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "userstories1.ser";

	/**
	 * Liefert ein Singleton zurück. Diese Methode ist thread-safe (oder...?) --> RICHTIG
	 * Nachteil: ggf. hoher Speicherbedarf, da Singleton zu Programmstart schon erzeugt
	 * @return
	 */
	public static Container getInstance() {
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception  {
		Container con = Container.getInstance();
		con.startEingabe(); 
	}
	
	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {
	
		String strInput = null;
		
		// Initialisierung des Eingabe-View
		Scanner scanner = new Scanner( System.in );

		// Ausgabe eines Texts zur Begruessung
		System.out.println("Prio-Tool V1.5 by Julius P. (dedicated to all my friends)");

		while ( true ) {
			System.out.print( "> "  );

			strInput = scanner.nextLine();
		
			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");	

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if ( strings[0].equals("help") ) {
				System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump....");
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("dump") ) {
				startAusgabe();
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("enter") ) {
				// Daten einlesen ...
				// this.addUserStory( userStory ) um das Objekt in Liste einzufügen
			}
								
			if (  strings[0].equals("store")  ) {
				this.store();
			}
		} // Ende der Schleife
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		java.util.Collections.sort( this.liste );

		// Klassische Ausgabe ueber eine For-Each-Schleife
		for (UserStory us : liste) {
			System.out.println(us.toString());
		}

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9)? Gerne auch mit Beachtung der neuen US1
		// (Filterung Aufwand > x)
		liste.stream().filter( userStory -> userStory.getAufwand() > 4  )   // Filter
				.filter( userStory -> userStory.getPrio() < 2.0 )
				.sorted(  (us1, us2)  -> Double.compare( us1.getPrio() , us2.getPrio() ) ) // MAP
				.forEach( userStory -> System.out.println( userStory.toString() ) ); // Reduce
	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 * 
	 */
	private void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			// Hier vermutet Herr P. ein bekanntes Design Pattern, ist sich aber auch nicht sicher
			// (welches denn...?) --> Decorator (nicht behandelt im WS 20/21)
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject( this.liste );
			System.out.println( this.size() + " User Stories wurden erfolgreich gespeichert!"); 
		}
		catch (IOException e) {
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler im Laden");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * 
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);
		  
		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;

		  }
		  System.out.println("Es wurden " + this.size() + " User Stories erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG: Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG: Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen einer Story unter Wahrung der Schlüsseleigenschaft
	 * @param r
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory r ) throws ContainerException {		
		if ( contains(r) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(r);
		
	}

	/**
	 * Prüft, ob eine Story bereits vorhanden ist
	 * @param r
	 * @return
	 */
	private boolean contains(UserStory r) {
		int ID = r.getId();
		for ( UserStory rec : liste) {
			if ( rec.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory-Objekten
	 * @return
	 */
	public int size(){
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte Story zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory rec : liste) {
			if (id == rec.getId() ){
				return rec;
			}
		}
		return null;
	}



}
