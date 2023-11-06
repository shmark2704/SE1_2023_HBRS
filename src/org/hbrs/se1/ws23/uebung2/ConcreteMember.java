package org.hbrs.se1.ws23.uebung2;

/*
 * Implementiert das Interface Member
 */

import java.io.Serializable;

public class ConcreteMember implements Member, Serializable {
    private Integer id;

    /*
     * erzeugt ein neues Objekt
     */
    public ConcreteMember(Integer id) {
        this.id = id;
    }
    /*
     * gibt die ID zurück
     */
    @Override
    public Integer getID() {
        return id;
    }

    /*
     * für Container.dump() gebraucht
     * wirst einzeln die Member-Objekte auf die Console aus
     */
    @Override
    public String toString() {
        String result = "Member (ID = " + getID() + ")";
        return result;
    }
}
