package com.adam.user.domain.repository;

import com.adam.user.domain.Account;

public interface UserRepository {
    public Account save(Account account);
}
