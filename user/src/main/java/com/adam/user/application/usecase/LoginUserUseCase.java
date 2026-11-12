package com.adam.user.application.usecase;

import com.adam.user.application.command.LoginUserCommand;
import com.adam.user.application.mapper.DomainMapper;
import com.adam.user.domain.Account;
import com.adam.user.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service public class LoginUserUseCase {
    @Autowired private DomainMapper mapper;
    @Autowired private UserRepository repository;

    public Account execute(LoginUserCommand userInformation) {
        Account retrievedAccount =
                repository.findByUsername(userInformation.username());

        return retrievedAccount;
    }
}
