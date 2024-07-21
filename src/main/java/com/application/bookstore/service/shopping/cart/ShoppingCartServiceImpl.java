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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final CartItemService cartItemService;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartResponseDto findUserById(Long id) {
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
    public ShoppingCartResponseDto addToShoppingCart(Authentication authentication,
                                                     ShoppingCartRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + user.getId())
                );
        CartItem cartItem = cartItemService.addCartItem(shoppingCart, requestDto);
        shoppingCart.getCartItems().add(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto updateByCartId(
            Authentication authentication,
            Long id,
            PutCartItemRequestDto requestDto
    ) {
        cartItemService.updatedById(id, requestDto.getQuantity());
        User user = (User) authentication.getPrincipal();
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(user.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find"
                                + " shopping cart by user's id: " + user.getId())
                );
        return shoppingCartMapper.toDto(shoppingCart);
    }
}
