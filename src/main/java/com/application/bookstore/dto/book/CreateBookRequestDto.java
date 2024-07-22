package com.application.bookstore.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    @NotBlank(message = "title can't be empty")
    @Size(min = 2, max = 20, message = "length should be between 2 and 20")
    private String title;
    @NotBlank(message = "author can't be empty")
    private String author;
    @NotNull(message = "isbn can't be empty")
    @ISBN(message = "invalid isbn format")
    private String isbn;
    @NotNull(message = "price can't be empty")
    @Positive(message = "price should be positive")
    private BigDecimal price;
    private String description;
    private String coverImage;
}

