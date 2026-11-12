package com.adam.user.infrastructure.persistence.repository;

import com.adam.user.infrastructure.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<AccountEntity, UUID> {
    public Optional<AccountEntity> findByUsername(String username);
}
