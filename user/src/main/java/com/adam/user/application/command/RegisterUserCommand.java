package com.adam.user.application.command;

import com.adam.user.application.ProfileInfoCommand;

public record RegisterUserCommand(
        String username,
        String email,
        String password,
        ProfileInfoCommand profileInfo
) { }
