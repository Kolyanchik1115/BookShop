package com.application.bookstore.dto.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CreateCategoryRequestDto {
    @NotNull
    @Length(min = 4, max = 20, message = "length should be between 4 and 20")
    private String name;
    private String description;
}
