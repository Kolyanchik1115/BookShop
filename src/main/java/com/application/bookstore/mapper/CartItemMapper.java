package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.cart.item.CartItemResponseDto;
import com.application.bookstore.dto.cart.item.PutCartItemRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.model.Book;
import com.application.bookstore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemResponseDto toDto(CartItem cartItem);

    CartItem toCartItem(PutCartItemRequestDto requestDto);

    @Mapping(target = "book", source = "bookId", qualifiedByName = "bookFromId")
    CartItem toCartItem(ShoppingCartRequestDto requestDto);

    @Named("bookFromId")
    default Book bookFromId(Long id) {
        Book book = new Book();
        book.setId(id);
        return book;
    }
}
