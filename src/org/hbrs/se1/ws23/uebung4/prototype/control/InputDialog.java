package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class InputDialog {
    private final static String LOCATION = "allStories.ser";

    private List<UserStory> userStoryList = Container.getInstance().getCurrentList();

    public static String getLocation() {
        return LOCATION;
    }


    public void startEingabe() throws ContainerException, Exception {
        String strInput = null;

        // Initialisierung des Eingabe-View
        // ToDo: Funktionsweise des Scanners erklären (F3)
        Scanner scanner = new Scanner( System.in );

        // Ausgabe eines Texts zur Begruessung
        System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");

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
                // this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.
            }

            if (  strings[0].equals("store")  ) {
                // Beispiel-Code
                UserStory userStory = new UserStory();
                userStory.setId(22);
                Container.getInstance().addUserStory(userStory);
                try {
                    this.store();
                } catch (ContainerException e) {
                    e.printStackTrace();
                }
            }
        } // Ende der Schleife
    }
    /*
     * Diese Methode realisiert eine Eingabe ueber einen Scanner
     * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
     * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
     */

    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe() {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        // Todo: Implementierung Sortierung (F4)
        Collections.sort(userStoryList, ((o1, o2) -> (int) (o1.getPrio() - o2.getPrio())));

        // Klassische Ausgabe ueber eine For-Each-Schleife
        for (UserStory story : userStoryList) {
            System.out.println(story.toString());
        };

        // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
        //  Gerne auch mit Beachtung der neuen US1
        // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
        // Todo: Implementierung Filterung mit Lambda (F5)
        List<UserStory> listMitName = userStoryList.stream()
                .filter(userstory -> userstory.getProject().equals("Coll@HBRS"))
                .filter(userstory -> userstory.getRisk() <= 5 )
                .collect(Collectors.toList());

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
            fos = new FileOutputStream(LOCATION);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(userStoryList);
            System.out.println( userStoryList.size() + " UserStory wurden erfolgreich gespeichert!");
        }
        catch (IOException e) {
            e.printStackTrace();
            //  Delegation in den aufrufendem Context
            // (Anwendung Pattern "Chain Of Responsibility)
            throw new ContainerException("Fehler beim Abspeichern");
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
            fis = new FileInputStream(LOCATION);
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                userStoryList = (List) obj;
            }
            System.out.println("Es wurden " + userStoryList.size() + " UserStory erfolgreich reingeladen!");
        }
        catch (IOException e) {
            System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
        }
        catch (ClassNotFoundException e) {
            System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
        }
        finally {
            if (ois != null) try { ois.close(); } catch (IOException e) {}
            if (fis != null) try { fis.close(); } catch (IOException e) {}
        }
    }

}
