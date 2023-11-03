package org.hbrs.se1.ws23.uebung2;

import java.util.List;

public class MemberView {

    // wirft alle Member-Objekte aus Liste raus
    public void dump(List<Member> liste) {
        for (Member existingMember : liste) {
            System.out.println(existingMember.toString());
        }
    }
}
