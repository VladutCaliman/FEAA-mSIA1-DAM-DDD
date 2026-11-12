package com.adam.user.infrastructure.persistence.entity;

import com.adam.user.domain.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor @Getter @Setter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "profile")
@Entity public class ProfileEntity {

    @Column(name = "accountId", nullable = false, unique = true)
    @Id private UUID accountId;

    @Column(name = "name", nullable = false, unique = false)
    private String name;

    @Column(name = "bio", nullable = false, unique = false)
    private String bio;
}
