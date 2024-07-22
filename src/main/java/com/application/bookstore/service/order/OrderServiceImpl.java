package com.application.bookstore.service.order;

import com.application.bookstore.dto.order.OrderRequestDto;
import com.application.bookstore.dto.order.OrderResponseDto;
import com.application.bookstore.dto.order.UpdateOrderRequestDto;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.mapper.OrderMapper;
import com.application.bookstore.model.CartItem;
import com.application.bookstore.model.Order;
import com.application.bookstore.model.OrderItem;
import com.application.bookstore.model.ShoppingCart;
import com.application.bookstore.model.User;
import com.application.bookstore.repository.cart.item.CartItemRepository;
import com.application.bookstore.repository.order.OrderRepository;
import com.application.bookstore.repository.order.item.OrderItemRepository;
import com.application.bookstore.repository.shopping.cart.ShoppingCartRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public OrderResponseDto placeOrder(Long id, OrderRequestDto requestDto) {

        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart by user id: " + id)
        );
        Order order = createOrder(id, shoppingCart, requestDto);
        Order savedOrder = orderRepository.save(order);
        Set<OrderItem> orderItems = getOrderItems(shoppingCart);
        savedOrder.setOrderItems(orderItems);
        orderItems.forEach(oi -> oi.setOrder(order));
        orderItemRepository.saveAll(orderItems);
        cartItemRepository.deleteAll(shoppingCart.getCartItems());
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getAll(Long id) {
        List<Order> orderList = orderRepository.findAllById(id);
        return orderList.stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    public OrderResponseDto updateStatus(Long id, UpdateOrderRequestDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find order by id: " + id)
        );
        order.setStatus(requestDto.getStatus());
        return orderMapper.toDto(orderRepository.save(order));
    }

    private Order createOrder(Long id, ShoppingCart shoppingCart, OrderRequestDto requestDto) {
        Order order = new Order();
        User user = new User();
        user.setId(id);
        order.setUser(user);
        order.setStatus(Order.Status.PROCESSING);
        order.setDeliveryAddress(requestDto.getDeliveryAddress());
        order.setTotal(
                shoppingCart.getCartItems()
                        .stream().map(ci -> ci.getBook()
                                .getPrice().multiply(BigDecimal.valueOf(ci.getQuantity()
                                ))).reduce(BigDecimal.ZERO, BigDecimal::add)

        );
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    private Set<OrderItem> getOrderItems(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems()
                .stream().map(this::convertToOrderItem)
                .collect(Collectors.toSet());
    }

    private OrderItem convertToOrderItem(CartItem cartItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setBook(cartItem.getBook());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getBook().getPrice());
        return orderItem;
    }
}
