package com.adam.user.domain.repository;

import com.adam.user.domain.Account;
import com.adam.user.infrastructure.persistence.entity.AccountEntity;

public interface UserRepository {
    public Account save(Account account);
    public Account findByUsername(String username);
}
