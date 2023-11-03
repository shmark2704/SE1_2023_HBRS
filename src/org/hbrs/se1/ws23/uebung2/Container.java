package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private List<Member> members = new ArrayList<>();
    private PersistenceStrategy<Member> persistenceStrategy = new PersistenceStrategyStream<>();

    /*
     * fügt ein neues Member in die ArrayList hinzu.
     * prüft, ob die MemberID bereits vorhanden ist
     * wirft eine geprüfte Exception raus
     */
    public void addMember(Member member) throws ContainerException {
        if (member.getID() == null) {
            throw new ContainerException(member);
        }
        for (Member existingMembers : members) {
            if (existingMembers.getID().equals(member.getID())) {
                throw new ContainerException(member);
            }
        }
        members.add(member);
    }
    /*
     * Löscht ein Member aus der ArrayList, basiert auf der ID des Members
     * Bei Fund und nicht Fund werden verschiedene String-return's rausgeworfen
     */
    public String deleteMember(Integer id)  {
        for (Member existingMember : members) {
            if (existingMember.getID().equals(id)) {
                members.remove(existingMember);
                return "Member Nr. " + id + " gelöscht!";
            }
        }
        return "Member Nr. " + id + " nicht gefunden!";
    }


    /*
     * gibt die Liste mit Member-Objekten zurück
     */
    public List<Member> getCurrentList() {
        return members;
    }

    /*
     * gibt die Größe der ArrayList aus
     */
    public int size() {
        return members.size();
    }


    public void store() throws PersistenceException {
        try {
            persistenceStrategy.openConnection();
            persistenceStrategy.save(members);
        } finally {
            persistenceStrategy.closeConnection();
        }
    }

    public void load() throws PersistenceException {
        try {
            List<Member> loadMember = persistenceStrategy.load();
            members.clear();
            members.addAll(loadMember);
        } finally {
            persistenceStrategy.closeConnection();
        }

    }



}
