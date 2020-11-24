package org.hbrs.se.ws20.uebung3.persistence;

import org.hbrs.se.ws20.uebung2.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class PersistenceStrategyMongoDBTest {
    PersistenceStrategyMongoDB<Member> psm;
    @BeforeEach
    void setup(){
        psm = new PersistenceStrategyMongoDB();
    }
    @Test
    void openConnection() throws PersistenceException {
        assertThrows(PersistenceException.class, () -> psm.openConnection());
    }

    @Test
    void closeConnection() {
        assertThrows(PersistenceException.class, () -> psm.closeConnection());
    }

    @Test
    void save() {
        assertThrows(UnsupportedOperationException.class, () -> psm.save(new LinkedList<Member>()));
    }

    @Test
    void load() {
        assertThrows(UnsupportedOperationException.class, () -> psm.load());
    }
}