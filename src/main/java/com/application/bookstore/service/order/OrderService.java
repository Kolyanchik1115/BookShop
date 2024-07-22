package com.application.bookstore.service.order;

import com.application.bookstore.dto.order.OrderRequestDto;
import com.application.bookstore.dto.order.OrderResponseDto;
import com.application.bookstore.dto.order.UpdateOrderRequestDto;
import java.util.List;

public interface OrderService {
    OrderResponseDto placeOrder(Long id, OrderRequestDto requestDto);

    List<OrderResponseDto> getAll(Long id);

    OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto requestDto);
}
