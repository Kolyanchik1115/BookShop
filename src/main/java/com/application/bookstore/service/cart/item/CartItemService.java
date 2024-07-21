package com.application.bookstore.service.cart.item;

import com.application.bookstore.dto.cart.item.CartItemResponseDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.model.CartItem;
import com.application.bookstore.model.ShoppingCart;

public interface CartItemService {

    CartItemResponseDto updatedById(Long id, int quantity);

    void deleteCartItem(Long id);

    CartItem addCartItem(ShoppingCart shoppingCart, ShoppingCartRequestDto requestDto);
}
