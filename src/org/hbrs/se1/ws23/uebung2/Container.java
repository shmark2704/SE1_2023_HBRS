package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> members = new ArrayList<>();

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
     * wirft aus die aktuell vorhandene Member-Objekte in der ArrayList
     */
    public void dump() {
        for (Member existingMember : members) {
            existingMember.toString();
        }
    }
    /*
     * gibt die Größe der ArrayList aus
     */
    public int size() {
        return members.size();
    }

}
