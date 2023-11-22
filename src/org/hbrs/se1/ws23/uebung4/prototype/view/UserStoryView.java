package org.hbrs.se1.ws23.uebung4.prototype.view;

import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.Collections;
import java.util.List;

public class UserStoryView {

    /**
     * Diese Methode realisiert die Ausgabe.
     */
    public void startAusgabe(List<UserStory> userStoryList) {

        // Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
        // ausgeben. Allerdings weiss der Student hier nicht weiter

        // [Sortierung ausgelassen]
        // Todo: Implementierung Sortierung (F4)
        try {
            Collections.sort(userStoryList, ((o1, o2) -> (int) (o1.getPrio() - o2.getPrio())));
        } catch (RuntimeException e) {
            System.out.println("Etwas ist schief gelaufen bei der Sortierung, versuchen sie nochmal! " + e);
        }

        // Klassische Ausgabe ueber eine For-Each-Schleife
        String s;
        for (UserStory story : userStoryList) {
            s = story.getId() + ", " + story.getTitel() + ", " + story.getMehrwert() + ", " + story.getStrafe() + ", " +
                    story.getAufwand() + ", " + story.getRisk() + " ," + story.getPrio();
            System.out.println(s);
        }

    }
}
