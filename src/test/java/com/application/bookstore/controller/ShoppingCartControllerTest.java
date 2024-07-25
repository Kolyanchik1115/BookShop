package com.application.bookstore.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.application.bookstore.dto.shopping.cart.ShoppingCartResponseDto;
import com.application.bookstore.model.Role;
import com.application.bookstore.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingCartControllerTest {
    private static final String URL_CART = "/cart";
    private static MockMvc mockMvc;
    private static ShoppingCartResponseDto shoppingCartResponseDto;
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll(@Autowired WebApplicationContext applicationContext) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity()).build();

        shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(1L);
        shoppingCartResponseDto.setUserId(1L);

        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setId(1L);
        role.setName(Role.RoleName.USER);
        User user = new User();
        user.setId(1L);
        user.setEmail("sprat@gmail.com");
        user.setPassword("12345678");
        user.setRoles(Set.of(role));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user,
                user.getPassword(),
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @WithMockUser(username = "user")
    @Test
    @DisplayName("Get user's cart")
    @Sql(scripts = {
            "classpath:db/shopping-cart/add-cart.sql",
            "classpath:db/cart-item/add-cart-item.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:db/cart-item/delete-cart-items.sql",
            "classpath:db/shopping-cart/delete-carts.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getShoppingCart_ValidRequest_ReturnsCorrectResponseDto() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(URL_CART)).andReturn();
        ShoppingCartResponseDto expected = shoppingCartResponseDto;

        ShoppingCartResponseDto actual = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                ShoppingCartResponseDto.class);
        Assertions.assertEquals(expected, actual,
                "Expected shopping cart should be: " + shoppingCartResponseDto
                        + " but was: " + actual
        );
    }
}
