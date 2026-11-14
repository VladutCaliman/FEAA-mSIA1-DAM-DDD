package com.adam.user.application.usecase;

import com.adam.user.application.command.LoginUserCommand;
import com.adam.user.application.mapper.DomainMapper;
import com.adam.user.domain.Account;
import com.adam.user.domain.repository.UserRepository;
import com.adam.user.shared.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service public class LoginUserUseCase {
    @Autowired private UserRepository repository;
    @Autowired private PasswordEncoder encoder;

    public Account execute(LoginUserCommand userInformation) {
        Account retrievedAccount =
                repository.findByUsername(userInformation.username());

        if(!encoder.matches(
                userInformation.password(),
                retrievedAccount.getPassword())
        ) throw new InvalidCredentialsException("Incorrect username or password. Please try again.");


        return retrievedAccount;
    }
}
