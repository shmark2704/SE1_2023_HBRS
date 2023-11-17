package org.hbrs.se1.ws23.uebung4.prototype.model;

import org.hbrs.se1.ws23.uebung4.prototype.control.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



public class InputDialog {
    private final static String LOCATION = "allStories.ser";

    private static List<UserStory> userStoryList = Container.getInstance().getCurrentList();

    public static String getLocation() {
        return LOCATION;
    }


    public static void startEingabe() throws IOException, ContainerException {
        String strInput;

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
                    UserStory userStory;
                    System.out.println("Bitte die Folgenden Daten eingeben und jeweils mit einer Leertaste separieren: 1)id, 2)titel, 3)mehrwert, " +
                            "4)strafe, 5)aufwand, 6)risiko, 7)2priorität");
                    try {
                        strings = scanner.nextLine().split(" ");
                        userStory = new UserStory(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]),
                                Integer.parseInt(strings[3]), Integer.parseInt(strings[4]), Integer.parseInt(strings[5]), Double.parseDouble(strings[6]));
                        Container.getInstance().addUserStory(userStory);
                        System.out.println("User Story erfolgreich hinzugefügt!");
                    } catch (NumberFormatException e) {
                        System.out.println("Falsche Eingabe. Bitte führen sie den enter-Prompt nochmal aus");
                    }

                }
                // Daten einlesen ...
                // this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.

            if (  strings[0].equals("store")  ) {
                // Beispiel-Code
                UserStory userStory = new UserStory();
                userStory.setId(22);
                Container.getInstance().addUserStory(userStory);
                try {
                    Container.getInstance().store();
                } catch (ContainerException e) {
                    e.printStackTrace();
                }
            }

            if ( strings[0].equals("exit")) {
                System.out.println("Programm beendet.");
                break;
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
    public static void startAusgabe() {

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
}
