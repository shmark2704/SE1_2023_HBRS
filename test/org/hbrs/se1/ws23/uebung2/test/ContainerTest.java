package org.hbrs.se1.ws23.uebung2.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws23.uebung2.*;
import org.junit.jupiter.api.Test;

public class ContainerTest {

    static Container container = new Container();
    Member m1 = new ConcreteMember(7);
    Member m2 = new ConcreteMember(11);


    private static int count;

    //Create Container and it is not 'null'
    @Test
    void createObjectContainer() {
        Container c = new Container();
        assertNotNull(c);
    }
    //Add Member with ID7
    @Test
    void posTestAddID7() throws ContainerException {
        container.addMember(m1);
        count++;
        assertEquals(count,container.size());
    }

    //Add Member with ID11
    @Test
    void posTestAddID11() throws ContainerException {
        container.addMember(m2);
        count++;
        assertEquals(count,container.size());
    }

    //Member with the same ID already added
    @Test
    void negTestAddID7() {
        assertThrows(ContainerException.class, () -> container.addMember(m2));
    }

    //Delete Member with ID7
    @Test
    void posTestDeleteID7() {
        assertEquals("Member Nr. " + 7 + " gelöscht!", container.deleteMember(7));
        count--;
        assertEquals(count,container.size());
    }

    //delete Member with ID 11
    @Test
    void posTestDeleteID11() {
        assertEquals("Member Nr. " + 11 + " gelöscht!", container.deleteMember(11));
        count--;
        assertEquals(count,container.size());
    }

    //get correct size from Container
    @Test
    void posGetCorrectSize() {
        assertEquals(count, container.size());
    }

    //delete a nonexistent ID
    @Test
    void negDeleteNonExistedID()  {
        assertEquals("Member Nr. " + 9 + " nicht gefunden!", container.deleteMember(9));
    }

    //add Member with ID null
    @Test
    void negTestAddMemberIDNull()  {
        assertThrows(ContainerException.class, () -> container.addMember(new ConcreteMember(null)));
    }


    @Test
    void posTestDump() {
        //hilfe :(
    }
}
