package com.application.bookstore.service.cart.item;

import com.application.bookstore.dto.cart.item.CartItemResponseDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.mapper.CartItemMapper;
import com.application.bookstore.model.CartItem;
import com.application.bookstore.model.ShoppingCart;
import com.application.bookstore.repository.book.BookRepository;
import com.application.bookstore.repository.cart.item.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepository;

    @Override
    public CartItemResponseDto updatedById(Long id, int quantity) {
        CartItem cartItemById = getById(id);
        cartItemById.setQuantity(quantity);
        return cartItemMapper.toDto(cartItemRepository.save(cartItemById));
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public CartItem addCartItem(ShoppingCart shoppingCart, ShoppingCartRequestDto requestDto) {

        CartItem cartItem = cartItemMapper.toCartItem(requestDto);
        cartItem.setShoppingCart(shoppingCart);
        Long bookId = requestDto.getBookId();
        cartItem.setBook(bookRepository.findById(bookId).orElseThrow(
                () -> new EntityNotFoundException("Can't find book by id: " + bookId)
        ));
        return cartItemRepository.save(cartItem);
    }

    private CartItem getById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(
                () -> new EntityNotFoundException("Can't find item by id: " + cartItemId)
        );
    }
}
