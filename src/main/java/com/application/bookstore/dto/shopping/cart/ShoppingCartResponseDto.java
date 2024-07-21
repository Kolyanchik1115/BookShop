package com.application.bookstore.dto.shopping.cart;

import com.application.bookstore.dto.cart.item.CartItemResponseDto;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class ShoppingCartResponseDto {
    private Long id;
    private Long userId;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<CartItemResponseDto> cartItems;

}
