package com.adam.user.infrastructure.persistence.adapter;

import com.adam.user.domain.Account;
import com.adam.user.domain.repository.UserRepository;
import com.adam.user.infrastructure.persistence.entity.AccountEntity;
import com.adam.user.infrastructure.persistence.mapper.EntityMapper;
import com.adam.user.infrastructure.persistence.repository.JpaUserRepository;
import com.adam.user.shared.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository public class UserRepositoryAdapter implements UserRepository {
    @Autowired private EntityMapper mapper;
    @Autowired private JpaUserRepository repository;

    @Override public Account save(Account account) {
        AccountEntity accEnty = mapper.toEntity(account);

        repository.save(accEnty);

        return account;
    }

    @Override public Account findByUsername(String username) {
        AccountEntity retrievedColumn =
                repository.findByUsername(username)
                        .orElseThrow(() -> new InvalidCredentialsException("No account found."));

        Account retrievedAccount = mapper.toDomain(retrievedColumn);

        return retrievedAccount;
    }
}
