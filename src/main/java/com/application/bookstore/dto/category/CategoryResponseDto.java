package com.application.bookstore.dto.category;

import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
