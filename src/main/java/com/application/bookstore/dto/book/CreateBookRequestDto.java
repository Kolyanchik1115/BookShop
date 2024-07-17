package com.application.bookstore.dto.book;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull
    @Size(min = 4, max = 20, message = "length should be between 4 and 20")
    private String title;
    @NotNull
    private String author;
    @NotNull
    @Pattern(regexp = "\\d{13}", message = "should be a number with length 13")
    private String isbn;
    @NotNull
    @Positive
    private BigDecimal price;
    private String description;
    private String coverImage;
}

