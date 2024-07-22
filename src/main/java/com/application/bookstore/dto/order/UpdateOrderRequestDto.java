package com.application.bookstore.dto.order;

import com.application.bookstore.model.Order.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    @NotNull
    private Status status;
}
