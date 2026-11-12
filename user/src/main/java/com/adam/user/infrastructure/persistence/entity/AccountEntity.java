package com.adam.user.infrastructure.persistence.entity;

import com.adam.user.domain.Profile;
import com.adam.user.domain.enums.AccountRole;
import com.adam.user.domain.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "account")
@Entity public class AccountEntity {

    @Column(name = "id", nullable = false, unique = true)
    @Id private UUID id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Embedded
    private EmailEmbeddable email;

    @Column(name = "password", nullable = false, unique = false)
    private String password;

    @Column(name = "status", nullable = false, unique = false)
    @Enumerated(EnumType.STRING) private AccountStatus status;

    @Column(name = "role", nullable = false, unique = false)
    @Enumerated(EnumType.STRING) private AccountRole role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "accountId")
    private ProfileEntity profile;
}
