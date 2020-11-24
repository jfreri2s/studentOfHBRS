package org.hbrs.se.ws20.uebung2;

import org.hbrs.se.ws20.uebung3.persistence.MemberView;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {
    Container container;
    MemberView mw;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    void setUp () {
        container = Container.getInstance();
        mw = new MemberView();
        try {
            container.addMember(new Create(1));
            container.addMember(new Create(2));
            container.addMember(new Create(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @AfterEach
    void tearDown() {
        container = null;
        System.setOut(standardOut);
        PersistenceStrategyStream.datei = "Memberlist.txt";
    }

    @Test
    void addMemberTest() throws ContainerException {
        assertEquals(container.deleteMember(1),"Member (ID = 1)");
        assertEquals(container.deleteMember(2),"Member (ID = 2)");
        assertEquals(container.deleteMember(3),"Member (ID = 3)");

        container.addMember(new Create(3));
        assertThrows(ContainerException.class, () -> container.addMember(new Create(3)));
    }

    @Test
    void deleteMemberTest() {
        assertEquals(container.deleteMember(1), "Member (ID = 1)");
        assertEquals(container.deleteMember(2), "Member (ID = 2)");
        assertEquals(container.deleteMember(3), "Member (ID = 3)");
        assertEquals(container.deleteMember(-5), "Die Zahl mit der ID -5 ist nicht vorhanden");
    }


    @Test
    void sizeTest() {
        assertEquals(container.size(), 3);
        container.deleteMember(1);
        assertEquals(container.size(), 2);
        container.deleteMember(2);
        assertEquals(container.size(), 1);
        container.deleteMember(3);
        assertEquals(container.size(), 0);
    }
    @Test
    void dumpTest(){
        mw.dump(container.getCurrentList());
        assertEquals("Member (ID = 1)\r\nMember (ID = 2)\r\nMember (ID = 3)",outputStreamCaptor.toString().trim());
    }
    @Test
    void getCurrenList(){
        LinkedList<Member> mb;
        mb = (LinkedList<Member>) container.getCurrentList();
        assertEquals(mb.size(),container.size());
    }
    @Test
    void persistentTest(){
        Container secondContainer = Container.getInstance();

        assertEquals(container.size(),secondContainer.size());
        assertEquals(container.getCurrentList(),secondContainer.getCurrentList());
        assertEquals(secondContainer.deleteMember(1), "Member (ID = 1)");
        assertEquals(secondContainer.deleteMember(2), "Member (ID = 2)");
        assertEquals(secondContainer.deleteMember(3), "Member (ID = 3)");
        assertEquals(secondContainer.deleteMember(-5), "Die Zahl mit der ID -5 ist nicht vorhanden");

        assertEquals(container.deleteMember(1), "Die Zahl mit der ID 1 ist nicht vorhanden");

    }
    @Test
    void loadTestFailed() {
        PersistenceStrategyStream.datei = null;
        assertThrows(PersistenceException.class, () ->container.load());
    }
    @Test
    void loadTestPassed(){
        container.setpSS(new PersistenceStrategyStream<Member>());
        try {
            container.load();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        mw.dump(container.getCurrentList());
        assertEquals("Member (ID = 1)\r\nMember (ID = 2)\r\nMember (ID = 3)",outputStreamCaptor.toString().trim());
    }
    @Test
    void saveTestFailed(){
        PersistenceStrategyStream.datei = null;
        assertThrows(PersistenceException.class, () ->container.store());
    }
    @Test
    void saveTestPassed() {
        container.setpSS(new PersistenceStrategyStream<Member>());
        try {
            container.store();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        loadTestPassed();
    }
}