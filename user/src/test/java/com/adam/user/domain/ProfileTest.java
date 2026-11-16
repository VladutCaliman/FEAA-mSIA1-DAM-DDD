package com.adam.user.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProfileTest {
    private Profile profile;

    @BeforeEach public void setUp() {
        profile = new Profile("Test Profile", "This is a test bio.");
    }

    @Test public void shouldCreateProfileSuccessfully() {
        assertEquals("Test Profile", profile.getName());
        assertEquals("This is a test bio.", profile.getBio());
    }

    @Test public void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profile(null, "Test bio"));
    }

    @Test public void shouldRejectBlankName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profile("   ", "Test bio"));
    }

    @Test public void shouldRejectNameShorterThanFiveCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profile("John", "Test bio"));
    }

    @Test public void shouldRejectNameLongerThanTwentyFiveCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profile("abcdefghijklmnopqrstuvwxyz", "Test bio"));
    }

    @Test public void shouldAcceptNameWithExactlyFiveCharacters() {
        assertDoesNotThrow(() ->
                new Profile("abcde", "Test bio"));
    }

    @Test public void shouldAcceptNameWithExactlyTwentyFiveCharacters() {
        assertDoesNotThrow(() ->
                new Profile("abcdefghijklmnopqrstuvwxy", "Test bio"));
    }

    @Test public void shouldRejectNullBio() {
        assertThrows(IllegalArgumentException.class,
                () -> new Profile("Test Profile", null));
    }

    @Test public void shouldRejectBioLongerThan255Characters() {
        String longBio = "a".repeat(256);

        assertThrows(IllegalArgumentException.class,
                () -> new Profile("Test Profile", longBio));
    }

    @Test public void shouldAcceptBioWithExactly255Characters() {
        String bio = "a".repeat(255);

        assertDoesNotThrow(() ->
                new Profile("Test Profile", bio));
    }

    @Test public void shouldAcceptEmptyBio() {
        assertDoesNotThrow(() ->
                new Profile("Test Profile", ""));
    }

    @Test public void shouldChangeNameSuccessfully() {
        profile.changeName("New Profile");

        assertEquals("New Profile", profile.getName());
    }

    @Test public void shouldRejectNullNameWhenChangingName() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeName(null));
    }

    @Test public void shouldRejectBlankNameWhenChangingName() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeName("   "));
    }

    @Test public void shouldRejectNameShorterThanFiveCharactersWhenChangingName() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeName("John"));
    }

    @Test public void shouldRejectNameLongerThanTwentyFiveCharactersWhenChangingName() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeName("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test public void shouldAcceptNameWithExactlyFiveCharactersWhenChangingName() {
        assertDoesNotThrow(() -> profile.changeName("abcde"));

        assertEquals("abcde", profile.getName());
    }

    @Test public void shouldAcceptNameWithExactlyTwentyFiveCharactersWhenChangingName() {
        String name = "abcdefghijklmnopqrstuvwxy";

        assertDoesNotThrow(() -> profile.changeName(name));

        assertEquals(name, profile.getName());
    }

    @Test public void shouldKeepOriginalNameWhenChangeNameFails() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeName("John"));

        assertEquals("Test Profile", profile.getName());
    }

    @Test public void shouldChangeBioSuccessfully() {
        profile.changeBio("New bio description.");

        assertEquals("New bio description.", profile.getBio());
    }

    @Test public void shouldRejectNullBioWhenChangingBio() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeBio(null));
    }

    @Test public void shouldRejectBioLongerThan255CharactersWhenChangingBio() {
        String longBio = "a".repeat(256);

        assertThrows(IllegalArgumentException.class,
                () -> profile.changeBio(longBio));
    }

    @Test public void shouldAcceptBioWithExactly255CharactersWhenChangingBio() {
        String bio = "a".repeat(255);

        assertDoesNotThrow(() -> profile.changeBio(bio));

        assertEquals(bio, profile.getBio());
    }

    @Test public void shouldAcceptEmptyBioWhenChangingBio() {
        assertDoesNotThrow(() -> profile.changeBio(""));

        assertEquals("", profile.getBio());
    }

    @Test public void shouldKeepOriginalBioWhenChangeBioFails() {
        assertThrows(IllegalArgumentException.class,
                () -> profile.changeBio(null));

        assertEquals("This is a test bio.", profile.getBio());
    }
}