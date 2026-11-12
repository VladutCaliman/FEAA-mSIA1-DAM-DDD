package com.adam.user.presentation.mapper;

import com.adam.user.application.command.RegisterUserCommand;
import com.adam.user.presentation.dto.request.RegisterInfoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandMapper {
    public RegisterUserCommand toCommand(RegisterInfoDTO frontendPayload);
}
