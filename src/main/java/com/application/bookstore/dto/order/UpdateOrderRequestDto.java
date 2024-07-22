package com.application.bookstore.dto.order;

import com.application.bookstore.model.Order.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderRequestDto {
    @NotNull(message = "status should be not empty")
    private Status status;
}
