package com.adam.user.domain;

import com.adam.user.domain.enums.AccountRole;
import com.adam.user.domain.enums.AccountStatus;
import com.adam.user.domain.valueobject.Email;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Account {
    private UUID id;
    private String username;
    private Email email;
    private String password;
    private AccountStatus status;
    private AccountRole role;
    private Profile profile;

    public Account(String username,
                   String email,
                   String password,
                   Profile profileInfo) {

        this.id = UUID.randomUUID();

        this.username = username;
        this.email = new Email(email);
        this.password = password;

        this.status = AccountStatus.ACTIVE;
        this.role = AccountRole.STANDARD;

        this.profile = profileInfo;

        //validation rules
    }
}
