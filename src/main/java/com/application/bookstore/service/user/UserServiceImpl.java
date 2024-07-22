package com.application.bookstore.service.user;

import com.application.bookstore.dto.user.registration.UserRegistrationRequestDto;
import com.application.bookstore.dto.user.registration.UserResponseDto;
import com.application.bookstore.exception.RegistrationException;
import com.application.bookstore.mapper.UserMapper;
import com.application.bookstore.model.Role;
import com.application.bookstore.model.User;
import com.application.bookstore.repository.role.RoleRepository;
import com.application.bookstore.repository.user.UserRepository;
import com.application.bookstore.service.shopping.cart.ShoppingCartService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException(String.format("Can't register user with email %s",
                    requestDto.getEmail()));
        }
        User user = userMapper.toUser(requestDto);
        user.setRoles(Set.of(roleRepository.findByName(Role.RoleName.USER)));
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        User savedUser = userRepository.save(user);
        shoppingCartService.createShoppingCart(savedUser);
        return userMapper.toDto(userRepository.save(user));
    }
}
