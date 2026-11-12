package com.adam.user.application;

public record ProfileInfoCommand(
    String name,
    String bio
) { }
