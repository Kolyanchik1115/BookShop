package com.application.bookstore.dto.cart.item;

import lombok.Data;

@Data
public class CartItemResponseDto {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private Integer quantity;
}
