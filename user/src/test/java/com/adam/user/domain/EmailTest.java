package com.adam.user.domain;


import com.adam.user.domain.valueobject.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test public void shouldCreateEmailSuccessfully() {
        Email email = new Email("test@email.com");

        assertEquals("test@email.com", email.toString());
    }

    @Test public void shouldConvertEmailToLowercase() {
        Email email = new Email("TEST@EMAIL.COM");

        assertEquals("test@email.com", email.toString());
    }

    @Test public void shouldAcceptEmailWithSubdomain() {
        Email email = new Email("test@mail.example.com");

        assertEquals("test@mail.example.com", email.toString());
    }

    @Test public void shouldAcceptEmailWithNumbers() {
        Email email = new Email("test123@email.com");

        assertEquals("test123@email.com", email.toString());
    }

    @Test public void shouldAcceptEmailWithSpecialCharacters() {
        Email email = new Email("test.user+tag@email.com");

        assertEquals("test.user+tag@email.com", email.toString());
    }

    @Test public void shouldRejectNullEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email(null));
    }

    @Test public void shouldRejectBlankEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("   "));
    }

    @Test public void shouldRejectEmailWithoutAtSymbol() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("testemail.com"));
    }

    @Test public void shouldRejectEmailWithoutDomain() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test@"));
    }

    @Test public void shouldRejectEmailWithoutUsername() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("@email.com"));
    }

    @Test public void shouldRejectEmailWithoutTopLevelDomain() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test@email"));
    }

    @Test public void shouldRejectEmailWithOneCharacterTopLevelDomain() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test@email.c"));
    }

    @Test public void shouldRejectEmailWithSpaces() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test user@email.com"));
    }

    @Test public void shouldRejectEmailWithMultipleAtSymbols() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test@@email.com"));
    }

    @Test public void shouldRejectEmailShorterThanFiveCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("a@b"));
    }

    @Test public void shouldRejectEmailLongerThanTwentyFiveCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email("verylongemailaddress@example.com"));
    }
}
