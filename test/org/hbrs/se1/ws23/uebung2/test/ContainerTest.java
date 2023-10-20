package org.hbrs.se1.ws23.uebung2.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws23.uebung2.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ContainerTest {

    static Container container;

    @BeforeAll
    static void setUp() {
        container = new Container();
    }

    @Test
    void addMemberID7() throws ContainerException {
        container.addMember(new ConcreteMember(7));
        assertEquals(1,container.size());
    }

    @Test
    void addMemberID11() throws ContainerException {
        container.addMember(new ConcreteMember(11));
        assertEquals(2,container.size());
    }

    /*@Test
    void dumpMethod() {
        for(ConcreteMember member : container) {
            assertEquals("Member (ID = " + member.getID() + ")", member.toString());
        }
    }

     */

    @Test
    void getCorrectSize() {
        assertEquals(2, container.size());
    }

    @Test
    void falseRemoveID9() throws ContainerException {
        assertEquals("Member Nr. " + 9 + " nicht gefunden!", container.deleteMember(9));
    }

    @Test
    void falseAddID7() throws ContainerException {
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(7)));
    }

    @Test
    void deleteMemberID11() throws ContainerException {
        assertEquals("Member Nr. " + 11 + " gelöscht!", container.deleteMember(11));
    }

    @Test
    void deleteMemberID7() throws ContainerException {
        assertEquals("Member Nr. " + 7 + " gelöscht!", container.deleteMember(7));
    }

    @Test
    void addMemberIDNull() throws ContainerException {
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(null)));
    }
}
