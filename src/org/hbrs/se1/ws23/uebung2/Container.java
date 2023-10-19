package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> members = new ArrayList<>();
    private int count;


    public void addMember(Member member) throws ContainerException {
        for (Member existingMembers : members) {
            if (existingMembers.getID() == member.getID() || member.getID() == null) {
                throw new ContainerException(member);
            }
        }
        members.add(member);
        count++;
    }

    public String deleteMember(Integer id) throws ContainerException {
        for (Member existingMember : members) {
            if (existingMember.getID() == id) {
                members.remove(existingMember);
                count--;
                return "Member Nr. " + id + " gelöscht!";
            }
        }
        return "Member Nr. " + id + " nicht gefunden!";
    }

    public void dump() {
        for (Member existingMember : members) {
            existingMember.toString();
        }
    }

    public int size() {
        return count;
    }

}
