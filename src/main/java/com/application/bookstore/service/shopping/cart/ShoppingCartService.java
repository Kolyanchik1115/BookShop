package com.application.bookstore.service.shopping.cart;

import com.application.bookstore.dto.cart.item.PutCartItemRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import com.application.bookstore.model.User;

public interface ShoppingCartService {

    ShoppingCartResponseDto findByUserId(Long id);

    void createShoppingCart(User user);

    ShoppingCartResponseDto addToShoppingCart(Long userId,
                                              ShoppingCartRequestDto requestDto);

    ShoppingCartResponseDto updateByCartId(
            Long userId,
            Long id,
            PutCartItemRequestDto requestDto
    );

}
