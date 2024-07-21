package com.application.bookstore.service.shopping.cart;

import com.application.bookstore.dto.cart.item.PutCartItemRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import com.application.bookstore.model.User;
import org.springframework.security.core.Authentication;

public interface ShoppingCartService {

    ShoppingCartResponseDto findUserById(Long id);

    void createShoppingCart(User user);

    ShoppingCartResponseDto addToShoppingCart(Authentication authentication,
                                              ShoppingCartRequestDto requestDto);

    ShoppingCartResponseDto updateByCartId(
            Authentication authentication,
            Long id,
            PutCartItemRequestDto requestDto
    );

}
