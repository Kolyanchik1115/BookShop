package com.application.bookstore.service.order.item;

import com.application.bookstore.dto.order.item.OrderItemResponseDto;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.mapper.OrderItemMapper;
import com.application.bookstore.model.OrderItem;
import com.application.bookstore.repository.order.item.OrderItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getAllById(Long id) {
        return orderItemRepository.findAllById(id)
                .stream().map(orderItemMapper::toDto).toList();
    }

    @Override
    public OrderItemResponseDto getItemById(Long id, Long itemId) {
        OrderItem orderItem = orderItemRepository.findById(id, itemId).orElseThrow(
                () -> new EntityNotFoundException("Can't find item by id: " + id)
        );
        return orderItemMapper.toDto(orderItem);
    }
}
