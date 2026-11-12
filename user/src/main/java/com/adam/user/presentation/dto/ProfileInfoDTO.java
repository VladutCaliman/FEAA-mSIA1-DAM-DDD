package com.adam.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfileInfoDTO(
    @NotNull @NotBlank @Size(min = 5, max = 25) String name,
    @NotNull @Size(max = 255) String bio
) { }
