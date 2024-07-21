package com.application.bookstore.dto.cart.item;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddCartItemRequestDto {
    @Positive
    private Long bookId;
    @Positive
    private Integer quantity;
}
