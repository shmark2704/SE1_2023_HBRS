package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;
import org.hbrs.se1.ws23.uebung4.prototype.model.exception.ContainerException;
import org.hbrs.se1.ws23.uebung4.prototype.view.UserStoryView;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;



public class InputDialog {
    private final static String LOCATION = "allStories.ser";

    private final List<UserStory> userStoryList = Container.getInstance().getCurrentList();

    public static String getLocation() {
        return LOCATION;
    }



    public void startEingabe() throws IOException, ContainerException, PersistenceException, ClassNotFoundException {
        String strInput;

        // Initialisierung des Eingabe-View
        // ToDo: Funktionsweise des Scanners erklären (F3)
        // 1. ein Scanner wird initialisiert
        // 2. mit scanner.nextLine() wird der strInput durch den Terminal aufgenommen
        // 3. strInput wird durch split() aufgeteilt, sodass die einzelne Werte als String-Array-Elemente dargestellt werden
        // 4. strInput[0] wird mit einzelnen if-Statements vergleicht und wenn übereinstimmt, dann auch interagiert
        // 5.
        Scanner scanner = new Scanner(System.in);

        // Ausgabe eines Texts zur Begruessung
        System.out.println("UserStory-Tool V1.0 by Julius P. (dedicated to all my friends)");

        while (true) {

            System.out.print("> ");

            strInput = scanner.nextLine();

            // Extrahiert ein Array aus der Eingabe
            String[] strings = strInput.split(" ");

            // 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
            if (strings[0].equals("help") && strings.length==1) {
                System.out.println("Folgende Befehle stehen zur Verfuegung: help, dump, load, store, enter, exit");
                continue;
            }
            // Auswahl der bisher implementierten Befehle:
            if (strings[0].equals("dump") && strings.length==1) {
                if (Container.getInstance().getCurrentList().size() == 0) {
                    System.out.println("Es gibt keine User Stories auf dem Speicher");
                }
                UserStoryView view = new UserStoryView();
                view.startAusgabe(userStoryList);
                continue;
            }
            // Auswahl der bisher implementierten Befehle:
            if (strings[0].equals("enter") && strings.length==1) {
                UserStory userStory;
                System.out.println("Bitte die Folgenden Daten eingeben und jeweils mit einer Leertaste separieren: 1)id, 2)titel, 3)mehrwert, " +
                        "4)strafe, 5)aufwand, 6)risiko, 7)priorität");
                try {
                    strings = scanner.nextLine().split(" ");
                    userStory = new UserStory(Integer.parseInt(strings[0]), strings[1], Integer.parseInt(strings[2]),
                            Integer.parseInt(strings[3]), Integer.parseInt(strings[4]), Integer.parseInt(strings[5]), Double.parseDouble(strings[6]));
                    System.out.println("Wollen Sie noch die User Story zu einem Projekt hinzufügen?");
                    System.out.println("(y/n)");
                    String s = scanner.next();
                    if (s.equals("y")) {
                        System.out.println("Geben Sie den Titel des Projekts ein.");
                        s = scanner.next();
                        userStory.setProject(s);
                    } else {
                        userStory.setProject("");
                    }
                    Container.getInstance().addUserStory(userStory);
                    System.out.println("User Story erfolgreich hinzugefügt!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Falsche Eingabe. Bitte führen sie den enter-Prompt nochmal aus " + e);
                }
                    scanner.nextLine();
                    continue;
            }
            // Daten einlesen ...
            // this.addUserStory( new UserStory( data ) ) um das Objekt in die Liste einzufügen.

            if (strings[0].equals("store") && strings.length==1) {
                Container.getInstance().store();
                continue;
            }

            if (strings[0].equals("exit") && strings.length==1) {
                System.out.println("Programm beendet.");
                break;
            }

            if (strings[0].equals("load") && strings.length==1) {
                Container.getInstance().load();
                continue;
            }

            if (strings[0].equals("dump") && strings[1].equals("project") && strings.length == 3) {
                String p = strings[2];
                List<UserStory> filterList = Util.sucheUserStory(p);
                if (filterList.isEmpty()) {
                    System.out.println("Dieses Projekt beinhaltet keine User Stories.");
                }
                for (UserStory story : filterList) {
                        System.out.println(story.getId() + ", " + story.getTitel() + ", " + story.getMehrwert() + ", " + story.getStrafe() + ", " +
                                story.getAufwand() + ", " + story.getRisk() + ", " + story.getPrio());
                }
                continue;
            }

            if (strings[0].equals("search") && strings.length==1) {
                System.out.println("Geben Sie die ID ein:");
                strInput = scanner.next();
                strings = strInput.split(" ");
                Util.getUserStory(Integer.parseInt(strings[0]));
                scanner.nextLine();
                continue;
            }
            System.out.println("Command nicht erkannt, bitte versuchen sie nochmal!");
            }


        } // Ende der Schleife
    /*
     * Diese Methode realisiert eine Eingabe ueber einen Scanner
     * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
     * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
     */

    // [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
    //  Gerne auch mit Beachtung der neuen US1
    // (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
    // Todo: Implementierung Filterung mit Lambda (F5)

}

    /*
     * Methode zum Speichern der Liste. Es wird die komplette Liste
     * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
     *
     */

