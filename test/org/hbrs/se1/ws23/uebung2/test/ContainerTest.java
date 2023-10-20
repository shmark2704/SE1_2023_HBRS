package org.hbrs.se1.ws23.uebung2.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws23.uebung2.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ContainerTest {

    static Container container;
    private static int count;

    @BeforeAll
    static void setUp() {
        container = new Container();
        count = 0;
    }

    @Test
    void MemberID7FullTest() throws ContainerException {
        container.addMember(new ConcreteMember(7));
        count++;
        assertEquals(count,container.size());
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(7)));
        assertEquals("Member Nr. " + 7 + " gelöscht!", container.deleteMember(7));
        count--;
        assertEquals(count,container.size());
    }

    @Test
        void ID11FullTest() throws ContainerException {
        container.addMember(new ConcreteMember(11));
        count++;
        assertEquals(count,container.size());
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(11)));
        assertEquals("Member Nr. " + 11 + " gelöscht!", container.deleteMember(11));
        count--;
        assertEquals(count,container.size());
    }

    /*@Test
    void posNr3DumpMethod() {
        for(ConcreteMember member : container) {
            assertEquals("Member (ID = " + member.getID() + ")", member.toString());
        }
    }

     */

    @Test
    void posGetCorrectSize() {
        assertEquals(count, container.size());
    }

    @Test
    void negRemoveNonExistedID()  {
        assertEquals("Member Nr. " + 9 + " nicht gefunden!", container.deleteMember(9));
    }


    @Test
    void negAddMemberIDNull()  {
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(null)));
    }

}
