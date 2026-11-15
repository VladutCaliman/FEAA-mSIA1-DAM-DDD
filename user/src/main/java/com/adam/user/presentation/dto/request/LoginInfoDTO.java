package com.adam.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginInfoDTO(
        @NotNull @NotBlank @Size(min = 5, max = 25) String username,
        @NotNull @NotBlank @Size(min = 8, max = 48) String password
) { }
