package com.adam.user.presentation.dto.request;

import com.adam.user.presentation.dto.ProfileInfoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterInfoDTO(
        @NotNull @NotBlank @Size(min = 5, max = 25) String username,
        @NotNull @NotBlank @Size(min = 5, max = 25) String email,
        @NotNull @NotBlank @Size(min = 8, max = 48) String password,
        @NotNull @Valid ProfileInfoDTO profileInfo
) { }
