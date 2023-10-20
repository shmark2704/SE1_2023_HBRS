package org.hbrs.se1.ws23.uebung2;

public class ContainerException extends Exception {
    /*
     * für die Methode Container.addMember() benötigt
     * wirft ein Exception-Text, wenn aufgerufen
     */
    public ContainerException(Member member) {
        super("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
    }
}
