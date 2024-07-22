package com.application.bookstore.dto.order;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class OrderRequestDto {
    @Length(min = 4, max = 60, message = "can't be less than 4 and more than 60 letters")
    private String deliveryAddress;
}
