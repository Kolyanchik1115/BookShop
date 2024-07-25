package com.application.bookstore.repository.shopping.cart;

import com.application.bookstore.model.ShoppingCart;
import com.application.bookstore.model.User;
import java.util.HashSet;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShoppingCartRepositoryTest {
    private static ShoppingCart shoppingCart;
    private static User user;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @BeforeAll
    static void beforeAll() {
        user = new User();
        user.setId(1L);

        shoppingCart = new ShoppingCart();
        shoppingCart.setId(1L);
        shoppingCart.setUser(user);
        shoppingCart.setCartItems(new HashSet<>());
    }

    @Test
    @DisplayName("Find shopping cart by user's id")
    @Sql(scripts = {
            "classpath:db/shopping-cart/add-cart.sql",
            "classpath:db/cart-item/add-cart-item.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:db/cart-item/delete-cart-items.sql",
            "classpath:db/shopping-cart/delete-carts.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByUserId_ValidData_ReturnsCorrectShoppingCart() {
        Optional<ShoppingCart> expected = Optional.of(shoppingCart);
        Optional<ShoppingCart> actual = shoppingCartRepository.findByUserId(user.getId());
        Assertions.assertEquals(expected, actual,
                "Expected shopping cart should be: " + expected
                        + " but was: " + actual
        );
    }
}
