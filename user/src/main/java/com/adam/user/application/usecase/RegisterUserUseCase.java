package com.adam.user.application.usecase;

import com.adam.user.application.command.RegisterUserCommand;
import com.adam.user.application.mapper.DomainMapper;
import com.adam.user.domain.Account;
import com.adam.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class RegisterUserUseCase {
    @Autowired private DomainMapper mapper;
    @Autowired private UserRepository repository;

    public Account execute(RegisterUserCommand userInformation) {
        Account toBeRegisterdAccount = mapper.toDomain(userInformation);

        return repository.save(toBeRegisterdAccount);
    }
}
