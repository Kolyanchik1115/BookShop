package com.application.bookstore.controller;

import com.application.bookstore.dto.user.login.UserLoginRequestDto;
import com.application.bookstore.dto.user.login.UserLoginResponseDto;
import com.application.bookstore.dto.user.registration.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.registration.UserResponseDto;
import com.application.bookstore.exception.RegistrationException;
import com.application.bookstore.security.AuthenticationService;
import com.application.bookstore.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping(value = "/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }
}
