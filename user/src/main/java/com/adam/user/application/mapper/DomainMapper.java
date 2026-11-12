package com.adam.user.application.mapper;

import com.adam.user.application.command.RegisterUserCommand;
import com.adam.user.domain.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DomainMapper {
    public Account toDomain(RegisterUserCommand userInformation);
}
