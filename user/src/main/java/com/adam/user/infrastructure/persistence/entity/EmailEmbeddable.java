package com.adam.user.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor @Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable public class EmailEmbeddable {

    @Column(name = "email", nullable = false, unique = true)
    private String value;
}
