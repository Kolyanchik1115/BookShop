package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.user.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.UserResponseDto;
import com.application.bookstore.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toUser(UserRegistrationRequestDto requestDto);
}
