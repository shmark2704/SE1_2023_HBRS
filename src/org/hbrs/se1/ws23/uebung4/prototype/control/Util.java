package org.hbrs.se1.ws23.uebung4.prototype.control;

import org.hbrs.se1.ws23.uebung4.prototype.model.Container;

import java.util.Collections;
import java.util.List;

public class Util {
    /**
     * Liefert eine bestimmte UserStory zurück
     * @param id
     * @return
     */

    private List<UserStory> list = Container.getInstance().getCurrentList();

    private UserStory getUserStory(int id) {
        for ( UserStory userStory : list) {
            if (id == userStory.getId() ){
                return userStory;
            }
        }
        return null;
    }
}
