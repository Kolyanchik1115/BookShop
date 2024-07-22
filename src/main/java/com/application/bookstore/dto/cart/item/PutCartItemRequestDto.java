package com.application.bookstore.dto.cart.item;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PutCartItemRequestDto {

    @Positive(message = "quantity should be positive")
    private Integer quantity;
}
