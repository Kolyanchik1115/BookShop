package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.order.OrderRequestDto;
import com.application.bookstore.dto.order.OrderResponseDto;
import com.application.bookstore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    OrderResponseDto toDto(Order order);

    Order toOrder(OrderRequestDto requestDto);
}
