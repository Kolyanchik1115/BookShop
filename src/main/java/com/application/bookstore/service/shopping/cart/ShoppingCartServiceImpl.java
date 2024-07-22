package com.application.bookstore.service.shopping.cart;

import com.application.bookstore.dto.cart.item.PutCartItemRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.mapper.ShoppingCartMapper;
import com.application.bookstore.model.CartItem;
import com.application.bookstore.model.ShoppingCart;
import com.application.bookstore.model.User;
import com.application.bookstore.repository.shopping.cart.ShoppingCartRepository;
import com.application.bookstore.service.cart.item.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto findByUserId(Long id) {
        return shoppingCartMapper.toDto(shoppingCartRepository
                .findByUserId(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + id)
                ));
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto addToShoppingCart(Long userId,
                                                     ShoppingCartRequestDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + userId)
                );
        CartItem cartItem = cartItemService.addCartItem(shoppingCart, requestDto);
        shoppingCart.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto updateByCartId(
            Long userId,
            Long id,
            PutCartItemRequestDto requestDto
    ) {
        cartItemService.updateById(id, requestDto.getQuantity());
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + userId)
                );
        return shoppingCartMapper.toDto(shoppingCart);
    }
}
