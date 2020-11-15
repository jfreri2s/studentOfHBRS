package org.hbrs.se.ws20.uebung2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class ContainerTest {
    Container container;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    void setUp() {
        container = new Container();
        try {
            container.addMember(new Create(1));
            container.addMember(new Create(2));
            container.addMember(new Create(3));

        } catch (ContainerException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        container = null;
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
    void dumpTest() {
        container.dump();
        assertEquals("Member (ID = 1)\r\nMember (ID = 2)\r\nMember (ID = 3)",outputStreamCaptor.toString().trim());
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

}