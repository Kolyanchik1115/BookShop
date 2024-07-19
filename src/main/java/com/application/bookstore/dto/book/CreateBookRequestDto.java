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
    @NotBlank
    @Size(min = 2, max = 20, message = "length should be between 2 and 20")
    private String title;
    @NotBlank
    private String author;
    @NotNull
    @ISBN
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    private String description;
    private String coverImage;
}

