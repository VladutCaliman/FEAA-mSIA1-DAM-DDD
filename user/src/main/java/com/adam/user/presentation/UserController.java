package com.adam.user.presentation;

import com.adam.user.application.usecase.LoginUserUseCase;
import com.adam.user.application.usecase.RegisterUserUseCase;
import com.adam.user.domain.Account;
import com.adam.user.domain.TokenProvider;
import com.adam.user.presentation.dto.request.LoginInfoDTO;
import com.adam.user.presentation.dto.request.RegisterInfoDTO;
import com.adam.user.presentation.mapper.CommandMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController @RequestMapping("/auth")
public class UserController {
    @Autowired private CommandMapper mapper;
    @Autowired private TokenProvider tokenizer;

    @Autowired private RegisterUserUseCase registerUser;
    @Autowired private LoginUserUseCase loginUser;

    @PostMapping("/register") public ResponseEntity<Void> register(
            @NotNull @Valid @RequestBody RegisterInfoDTO frontendAccountRegistrationInfo,
            HttpServletResponse response) {

        Account regiseredAccount = registerUser.execute(
                mapper.toCommand(frontendAccountRegistrationInfo)
        );

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                createCookie(tokenizer.generateTokenFrom(regiseredAccount))
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(null);
    }

    @PostMapping("/login") public ResponseEntity<Void> login(
            @NotNull @Valid @RequestBody LoginInfoDTO frontendAccountLoginInfo,
            HttpServletResponse response) {

        Account retrievedAccount = loginUser.execute(
                mapper.toCommand(frontendAccountLoginInfo)
        );

        response.addHeader(
                HttpHeaders.SET_COOKIE,
                createCookie(tokenizer.generateTokenFrom(retrievedAccount))
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    private String createCookie(String jwtoken) {
        ResponseCookie cookie = ResponseCookie
                .from("access_token", jwtoken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .build();

        return cookie.toString();
    }
}