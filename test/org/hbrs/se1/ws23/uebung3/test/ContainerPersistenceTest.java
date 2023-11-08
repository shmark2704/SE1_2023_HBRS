package org.hbrs.se1.ws23.uebung3.test;

import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws23.uebung2.*;
import org.hbrs.se1.ws23.uebung3.persistence.*;


import org.junit.jupiter.api.Test;


import java.util.List;


public class ContainerPersistenceTest {
    private final Container container = Container.getInstance();
    private final PersistenceStrategyStream<Member> psS = new PersistenceStrategyStream<>();
    private final PersistenceStrategyMongoDB<Member> psMongoDB = new PersistenceStrategyMongoDB<>();

    @Test
    void testStrategyNotSet() {
        assertThrows(NullPointerException.class, () -> container.store());
        assertThrows(NullPointerException.class, () -> container.load());
    }

    @Test
    void testMongoDBStrategy() {
        container.setPersistenceStrategy(psMongoDB);
        assertThrows(UnsupportedOperationException.class, () -> container.store());
        assertThrows(UnsupportedOperationException.class, () -> container.load());
    }

    @Test
    void testInvalidStreamLocation() {
        psS.setLocation("invalid/directory/test");
        container.setPersistenceStrategy(psS);
        assertThrows(PersistenceException.class, () -> container.store());
        assertThrows(PersistenceException.class, () -> container.load());
    }

    @Test
    void testRoundTrip() throws ContainerException, PersistenceException {
        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(2);

        psS.setLocation("C:\\Users\\Mark\\Desktop\\Downloads\\1.pdf");
        container.setPersistenceStrategy(psS);

        container.addMember(member1);
        container.addMember(member2);

        container.store();
        container.deleteMember(1);

        List<Member> currentList = container.getCurrentList();

        assertEquals(1, currentList.size());
        assertFalse(currentList.contains(member1));
        assertTrue(currentList.contains(member2));

        container.load();

        List<Member> newList = container.getCurrentList();
        Member backMember1 = newList.get(0);
        Member backMember2 = newList.get(1);
        assertEquals(member1.getID().intValue(), backMember1.getID().intValue());
        assertEquals(member2.getID().intValue(), backMember2.getID().intValue());

    }
}
