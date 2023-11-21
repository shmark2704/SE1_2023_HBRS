package org.hbrs.se1.ws23.uebung4.prototype.model;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung4.prototype.control.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;

import java.io.*;
import java.util.*;


/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Was ist ihre Strategie zur Wiederverwendung? (F1)
 * // Container mit Generic entwickeln und bei Instanziierung das richtige Generic-Objekt angeben
 *
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * ToDo: Wie bewerten Sie diese Entscheidung? (F2, F6)
 * // Aufteilung der Klasse in verschiedene Funktionalitätsklassen -> objekt-orientierte Refaktorisierung
 * // view, control, model - packages MVC
 */

public class Container<E> {
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo: Bewertung Thread-Safeness (F1)
	// Sicher
	// Nachteil: ggf. geringer Speicherbedarf, da Singleton zu Programmstart schon erzeugt wird
	// Todo: Bewertung Speicherbedarf (F1)
	// Hoher Speicherbedarf
	private static Container<UserStory> instance = new Container();
	
	// URL der Datei, in der die Objekte gespeichert werden 


	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static synchronized Container getInstance() {
		return instance;
	}

	private PSStream<UserStory> persistanceStrategy;

	public void setPersistanceStrategy(PSStream<UserStory> ps) {
		persistanceStrategy = ps;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	private Container(){
		liste = new ArrayList<>();
	}

	void store() throws IOException, PersistenceException {
		try {
			setPersistanceStrategy(new PSStream());
			persistanceStrategy.openConnection();
			persistanceStrategy.save(liste);
		} catch (PersistenceException e1) {
			throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "");
		} catch (ContainerException e2) {

		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 *
	 */
	void load() throws ClassNotFoundException, IOException, PersistenceException {
		try {
			List<UserStory> newListUserStory = persistanceStrategy.load();
			liste.clear();
			liste.addAll(newListUserStory);
			persistanceStrategy.closeConnection();
		} catch (RuntimeException e) {
			System.out.println("Etwas ist schief gelaufen in der Liste, versuchen sie es nochmal. " + e);
		}

	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return liste;
	}

	public void start() throws Exception {
		InputDialog inputDialog = new InputDialog();
		inputDialog.startEingabe();
	}

}
