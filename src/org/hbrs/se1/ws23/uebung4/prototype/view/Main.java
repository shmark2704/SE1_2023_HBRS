package org.hbrs.se1.ws23.uebung4.prototype.view;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

/**
 * Start-Methoden zum Starten des Programms
 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
 */
public class Main {
    public static void main (String[] args) throws Exception {
        // ToDo: Bewertung Exception-Handling (F3, F7)
        Container<UserStory> con = Container.getInstance();
        con.startEingabe();
    }
}
