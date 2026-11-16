package com.adam.user.domain;

import com.adam.user.domain.enums.AccountRole;
import com.adam.user.domain.enums.AccountStatus;
import com.adam.user.shared.exception.UnableToChangeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AccountTest {
    private Account account;
    private Profile profile;

    @BeforeEach public void setUp() {
        profile = new Profile("Test Profile", "Test bio");

        account = new Account(
                "TestUser",
                "test@email.com",
                "hashedPassword",
                profile
        );
    }

    @Test public void shouldCreateAccountSuccessfully() {
        assertNotNull(account.getId());
        assertEquals("testuser", account.getUsername());
        assertEquals("test@email.com", account.getEmail().toString());
        assertEquals("hashedPassword", account.getPassword());
        assertEquals(AccountStatus.ACTIVE, account.getStatus());
        assertEquals(AccountRole.STANDARD, account.getRole());
        assertEquals(profile, account.getProfile());
    }

    @Test public void shouldGenerateUniqueIds() {
        Account anotherAccount = new Account(
                "AnotherUser",
                "another@email.com",
                "password",
                profile
        );

        assertNotEquals(account.getId(), anotherAccount.getId());
    }

    @Test public void shouldConvertUsernameToLowercase() {
        Account newAccount = new Account(
                "TESTUSER",
                "test@email.com",
                "password",
                profile
        );

        assertEquals("testuser", newAccount.getUsername());
    }


    @Test public void shouldRejectNullUsername() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account(null, "test@email.com", "password", profile)
        );
    }

    @Test public void shouldRejectBlankUsername() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("   ", "test@email.com", "password", profile)
        );
    }

    @Test public void shouldRejectUsernameShorterThanFiveCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("abcd", "test@email.com", "password", profile)
        );
    }

    @Test public void shouldRejectUsernameLongerThanTwentyFiveCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("abcdefghijklmnopqrstuvwxyz",
                        "test@email.com", "password", profile)
        );
    }

    @Test public void shouldAcceptUsernameWithExactlyFiveCharacters() {
        assertDoesNotThrow(() ->
                new Account("abcde", "test@email.com", "password", profile)
        );
    }

    @Test public void shouldAcceptUsernameWithExactlyTwentyFiveCharacters() {
        assertDoesNotThrow(() ->
                new Account("abcdefghijklmnopqrstuvwxy",
                        "test@email.com", "password", profile)
        );
    }

    @Test public void shouldRejectNullPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testUser", "test@email.com", null, profile)
        );
    }

    @Test public void shouldRejectBlankPassword() {
        assertThrows(IllegalArgumentException.class, () ->
                new Account("testUser", "test@email.com", "   ", profile)
        );
    }

    @Test public void shouldChangePassword() {
        account.changePassword("newHashedPassword");

        assertEquals("newHashedPassword", account.getPassword());
    }

    @Test public void shouldRejectNullPasswordWhenChangingPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changePassword(null));
    }

    @Test public void shouldRejectBlankPasswordWhenChangingPassword() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changePassword("   "));
    }

    @Test public void shouldKeepOriginalPasswordWhenNewPasswordIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changePassword(""));

        assertEquals("hashedPassword", account.getPassword());
    }

    @Test public void shouldChangeEmail() {
        account.changeEmail("new@email.com");

        assertEquals("new@email.com", account.getEmail().toString());
    }

    @Test public void shouldConvertChangedEmailToLowercase() {
        account.changeEmail("NEW@EMAIL.COM");

        assertEquals("new@email.com", account.getEmail().toString());
    }

    @Test public void shouldRejectNullEmailWhenChangingEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changeEmail(null));
    }

    @Test public void shouldRejectInvalidEmailWhenChangingEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changeEmail("invalid-email"));
    }

    @Test public void shouldRejectEmailShorterThanFiveCharactersWhenChangingEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changeEmail("a@b"));
    }

    @Test public void shouldRejectEmailLongerThanTwentyFiveCharactersWhenChangingEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changeEmail("verylongemailaddress@example.com"));
    }

    @Test public void shouldKeepOriginalEmailWhenNewEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> account.changeEmail("invalid-email"));

        assertEquals("test@email.com", account.getEmail().toString());
    }

    @Test public void shouldPromoteStandardToModerator() {
        account.promote();

        assertEquals(AccountRole.MODERATOR, account.getRole());
    }

    @Test public void shouldNotPromoteModerator() {
        account.promote();

        assertThrows(UnableToChangeException.class,
                () -> account.promote());

        assertEquals(AccountRole.MODERATOR, account.getRole());
    }

    @Test public void shouldDemoteModeratorToStandard() {
        account.promote();
        account.demote();

        assertEquals(AccountRole.STANDARD, account.getRole());
    }

    @Test public void shouldNotDemoteStandardUser() {
        assertThrows(UnableToChangeException.class,
                () -> account.demote());

        assertEquals(AccountRole.STANDARD, account.getRole());
    }


    @Test public void shouldDeactivateActiveAccount() {
        account.deactivate();

        assertEquals(AccountStatus.INACTIVE, account.getStatus());
    }

    @Test public void shouldNotDeactivateInactiveAccount() {
        account.deactivate();

        assertThrows(UnableToChangeException.class,
                () -> account.deactivate());

        assertEquals(AccountStatus.INACTIVE, account.getStatus());
    }

    @Test public void shouldReactivateInactiveAccount() {
        account.deactivate();
        account.reactivate();

        assertEquals(AccountStatus.ACTIVE, account.getStatus());
    }

    @Test public void shouldNotReactivateActiveAccount() {
        assertThrows(UnableToChangeException.class,
                () -> account.reactivate());

        assertEquals(AccountStatus.ACTIVE, account.getStatus());
    }
}




