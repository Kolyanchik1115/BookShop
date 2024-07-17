package com.application.bookstore.service.user;

import com.application.bookstore.dto.user.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.UserResponseDto;
import com.application.bookstore.exception.RegistrationException;
import com.application.bookstore.mapper.UserMapper;
import com.application.bookstore.model.User;
import com.application.bookstore.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException(String.format("Can't register user with email %s",
                    requestDto.getEmail()));
        }
        User user = userMapper.toUser(requestDto);
        return userMapper.toDto(userRepository.save(user));
    }
}
