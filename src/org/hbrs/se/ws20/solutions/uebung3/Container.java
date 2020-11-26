package org.hbrs.se.ws20.solutions.uebung3;

import java.util.ArrayList;
import java.util.List;

import org.hbrs.se.ws20.solutions.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.solutions.uebung3.persistence.PersistenceStrategy;


/*
 * Klasse zum Abspeichern von Objekten in einer Liste
 *
 * c/o Sascha Alda, H-BRS, 2020
 *
 */

public class Container {

	// Interne ArrayList zur Abspeicherung der Objekte
	private List<Member> liste = null;

	//Statische Klassen-Variable, um die Referenz
	//auf das einzige Container-Objekt abzuspeichern
	// Dynamische Belegung: nur falls Methode getInstance geladen
	// wird, dann wird nach Bedarf die Variable mit einer Referenz gefüllt
	private static Container instance = null; // = new Container();

	private PersistenceStrategy strategy = null;

	/*
	 * Statische Methode um die einzige Instanz der Klasse
	 * Container zu bekommen. Das Keyword synchronized bewirkt,
	 * dass garantiert nur ein Objekt den alleinigen Zugriff
	 * auf diese Methode hat. Anonsten koennte es passieren, dass
	 * zwei parallel zugreifende Objekte zwei unterschiedliche
	 * Objekte erhalten (vgl. auch Erlaeuterung in Uebung)
	 *
	 */
	public static synchronized Container getInstance() {
		if (instance == null) {
			instance = new Container();
			System.out.println("Objekt vom Typ Container wurde instanziiert!");
		}
		return instance;
	}

	// Der statische Initialisierungsblock. Dient nur für Debug-Zwecke
	// zur Verdeutlichung, wann eine Klasse geladen wird.
	static {
		System.out.println("Klasse Container wurde geladen!");
		// instance = new Container();
	}



	/*
	 * Ueberschreiben des Konstruktors. Durch die Sichtbarkeit private
	 * kann man von aussen die Klasse nicht mehr instanziieren,
	 * sondern nur noch kontrolliert ueber die statische Methode
	 * der Klasse Container!
	 */
	private Container(){
		System.out.println("Container ist instanziiert (Konstruktor)!");
		this.liste = new ArrayList<Member>();
	}


	public List getCurrentList() {
		return this.liste;
	}

	/*
	 * Methode zum Hinzufuegen einer Member.
	 * @param r
	 * @throws ContainerException
	 */
	public void addMember ( Member r ) throws ContainerException {

		if ( contains( r ) == true ) {
			System.out.println("Duplikat: " + r.toString() );
			ContainerException ex = new ContainerException( ContainerException.ExceptionType.DuplicateMember );
			ex.addID ( r.getID() );
			throw ex;
		}
		liste.add(r);
	}

	/*
	 * Methode zur Ueberpruefung, ob ein Member-Objekt in der Liste enthalten ist
	 *
	 */
	private boolean contains( Member r) {
		Integer ID = r.getID();
		for ( Member rec : liste) {
			if ( rec.getID() == ID ) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Methode zum Loeschen einer Member
	 *
	 */
	public String deleteMember( Integer id ) {
		Member rec = getMember( id );
		if (rec == null) return "Member nicht enthalten - ERROR"; else {
			liste.remove(rec);
			return "Member mit der ID " + id + " konnte geloescht werden";
		}
	}

	/*
	 * Methode zur Bestimmung der Anzahl der von Member-Objekten
	 *
	 */
	public int size(){
		return liste.size();
	}



	/*
	 * Interne Methode zur Ermittlung eines Member
	 *
	 */
	private Member getMember(Integer id) {
		for ( Member rec : liste) {
			if (id == rec.getID().intValue() ){
				return rec;
			}
		}
		return null;
	}

	public static void printInfoAboutClass() {
		System.out.println("Class Info: c/o Sascha Alda, 2020");
	}

	public void load() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException( PersistenceException.ExceptionType.NoStrategyIsSet,  "Strategy not initialized");

		// this.openConnection();
		List<Member> liste = this.strategy.load();
		this.liste = liste; // MayBe merge
	}

	public void setPersistenceStrategie(PersistenceStrategy persistenceStrategy) {
		this.strategy = persistenceStrategy;
	}

	public void store() throws PersistenceException {
		if (this.strategy == null)
			throw new PersistenceException( PersistenceException.ExceptionType.NoStrategyIsSet,
					"Strategy not initialized");

		this.openConnection();
		this.strategy.save( this.liste  );
	}

	private void openConnection() throws PersistenceException {
		try {
			this.strategy.openConnection();
		} catch( java.lang.UnsupportedOperationException e ) {
			throw new PersistenceException( PersistenceException.ExceptionType.ImplementationNotAvailable , "Not implemented!" );
		}
	}

}
