package com.application.bookstore.controller;

import com.application.bookstore.dto.cart.item.PutCartItemRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartRequestDto;
import com.application.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import com.application.bookstore.model.User;
import com.application.bookstore.service.cart.item.CartItemService;
import com.application.bookstore.service.shopping.cart.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cart")
@Tag(name = "Shopping cart management", description = "Endpoints for shopping cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    @Operation(summary = "Get shopping cart", description = "Get all shopping cart details")
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.findUserById(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    @Operation(summary = "Add book to shopping cart", description = "Add book to shopping cart")
    public ShoppingCartResponseDto addItemToShoppingCart(
            Authentication authentication,
            @RequestBody @Valid ShoppingCartRequestDto requestDto) {
        return shoppingCartService.addToShoppingCart(authentication, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/cart-items/{id}")
    @Operation(
            summary = "Update books quantity",
            description = "Update quantity of a book in the shopping cart"
    )
    public ShoppingCartResponseDto updateById(
            Authentication authentication,
            @PathVariable Long id,
            @RequestBody @Valid PutCartItemRequestDto requestDto) {
        return shoppingCartService.updateByCartId(authentication, id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/cart-items/{id}")
    @Operation(
            summary = "Delete book from shopping cart",
            description = "Delete book from shopping cart by id"
    )
    public void deleteItemFromShoppingCart(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }

}
