package com.application.bookstore.service.user;

import com.application.bookstore.dto.user.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.UserResponseDto;
import com.application.bookstore.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
