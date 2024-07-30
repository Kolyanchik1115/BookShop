package com.application.bookstore.controller;

import com.application.bookstore.dto.user.google.UserGoogleRequestDto;
import com.application.bookstore.dto.user.google.UserGoogleResponseDto;
import com.application.bookstore.dto.user.login.UserLoginRequestDto;
import com.application.bookstore.dto.user.login.UserLoginResponseDto;
import com.application.bookstore.dto.user.registration.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.registration.UserResponseDto;
import com.application.bookstore.exception.RegistrationException;
import com.application.bookstore.security.AuthenticationService;
import com.application.bookstore.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
@Tag(name = "Authentication management", description = "Endpoints for authentication users")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    @Operation(summary = "Authorization", description = "User authorization")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping(value = "/registration")
    @Operation(summary = "Registration", description = "User registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping(value = "/google")
    @Operation(summary = "Google authorization", description = "User google authorization")
    public UserGoogleResponseDto google(@RequestBody @Valid UserGoogleRequestDto requestDto) {
        return userService.google(requestDto);
    }
}
