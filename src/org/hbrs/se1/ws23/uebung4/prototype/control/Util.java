package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;
import org.hbrs.se1.ws23.uebung4.prototype.model.UserStory;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
    /**
     * Liefert eine bestimmte UserStory zurück
     * @param id
     * @return
     */

    private static final List<UserStory> list = Container.getInstance().getCurrentList();

    public static UserStory getUserStory(int id) {
        for ( UserStory userStory : list) {
            if (id == userStory.getId() ){
                System.out.println("Diese User Story ist vorhanden im Speicher.");
                return userStory;
            }
        }
        System.out.println("Die User Story mit dieser ID existiert nicht.");
        return null;
    }

    public static List<UserStory> sucheUserStory(String projektName) {
        List<UserStory> listMitName = null;
        try {
            listMitName = list.stream()
                    .filter(userstory -> userstory.getProject().equals(projektName))
                    .collect(Collectors.toList());
        } catch (NullPointerException e) {
            System.out.println("Es gibt keine UserStories zum ausgeben " + e);
        }
        return listMitName;
    }
}
