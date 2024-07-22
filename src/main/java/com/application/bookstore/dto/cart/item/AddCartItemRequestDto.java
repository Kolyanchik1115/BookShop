package com.application.bookstore.dto.cart.item;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddCartItemRequestDto {
    @Positive(message = "bookId should be positive")
    private Long bookId;
    @Positive(message = "quantity should be positive")
    private Integer quantity;
}
