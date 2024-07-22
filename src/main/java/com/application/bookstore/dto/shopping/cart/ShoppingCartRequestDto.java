package com.application.bookstore.dto.shopping.cart;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ShoppingCartRequestDto {
    @Positive(message = "bookId should be positive")
    private Long bookId;
    @Positive(message = "quantity should be positive")
    private Integer quantity;
}
