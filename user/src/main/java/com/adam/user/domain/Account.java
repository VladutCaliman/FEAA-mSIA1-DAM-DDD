package com.adam.user.domain;

import com.adam.user.domain.enums.AccountRole;
import com.adam.user.domain.enums.AccountStatus;
import com.adam.user.domain.valueobject.Email;
import com.adam.user.shared.exception.UnableToChangeException;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.UUID;

@Getter @Setter
public class Account {
    private final UUID id;
    private final String username;
    private Email email;
    private String password;
    private AccountStatus status;
    private AccountRole role;
    private Profile profile;

    public Account(String username,
                   String email,
                   String password,
                   Profile profileInfo) {

        if (username == null || username.isBlank())
            throw new IllegalArgumentException("Username cannot be null or blank");
        if (username.length() < 5 || username.length() > 25)
            throw new IllegalArgumentException("Username must be between 5 and 25 characters");

        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");


        this.id = UUID.randomUUID();

        this.username = username.toLowerCase(Locale.ROOT);
        this.email = new Email(email);
        this.password = password;

        this.status = AccountStatus.ACTIVE;
        this.role = AccountRole.STANDARD;

        this.profile = profileInfo;
    }

    public void changeEmail(String newEmailContender) {
        this.setEmail(new Email(newEmailContender));
    }

    public void changePassword(String newPasswordHashed) {
        if (newPasswordHashed == null || newPasswordHashed.isBlank())
            throw new IllegalArgumentException("Password cannot be null or blank");

        this.setPassword(newPasswordHashed);
    }

    public void promote() {
        switch (role) {
            case STANDARD -> this.setRole(AccountRole.MODERATOR);
            case MODERATOR -> throw new UnableToChangeException("Cannot promote user.");
            case null, default -> throw new RuntimeException("Should be unreachable ON Account.promote()");
        }
    }

    public void demote() {
        switch (role) {
            case MODERATOR -> this.setRole(AccountRole.STANDARD);
            case STANDARD -> throw new UnableToChangeException("Cannot demote user.");
            case null, default -> throw new RuntimeException("Should be unreachable ON Account.demote()");
        }
    }

    public void deactivate() {
        switch (status) {
            case ACTIVE -> this.setStatus(AccountStatus.INACTIVE);
            case INACTIVE -> throw new UnableToChangeException("Cannot deactivate an already deactivated account.");
            case null, default -> throw new RuntimeException("Should be unreachable ON Account.deactivate()");
        }
    }

    public void reactivate() {
        switch (status) {
            case INACTIVE -> this.setStatus(AccountStatus.ACTIVE);
            case ACTIVE -> throw new UnableToChangeException("Cannot activate an already active account.");
            case null, default -> throw new RuntimeException("Should be unreachable ON Account.reactivate()");
        }
    }
}
