package com.adam.user.application.command;

public record LoginUserCommand(
    String username,
    String password
) { }
