package com.application.bookstore.repository.book;

import com.application.bookstore.model.Book;
import com.application.bookstore.model.Category;
import java.math.BigDecimal;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    private static Book book;
    @Autowired
    private BookRepository bookRepository;

    @BeforeAll
    static void beforeAll() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test category");
        category.setDescription("Category for tests");

        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("9783161484100");
        book.setPrice(BigDecimal.valueOf(17.50));
        book.setDescription("Description for test book");
        book.setCategories(Set.of(category));

    }

    @Test
    @DisplayName("Get user's cart")
    @Sql(scripts = {
            "classpath:db/book/delete-books.sql",
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void findAll_ValidBooks_ReturnsCorrectAmountOfBooks() {
        bookRepository.save(book);
        int expected = 1;
        Page<Book> actual = bookRepository.findAll(PageRequest.of(0, 1));
        Assertions.assertEquals(expected, actual.getSize(), "Expected amount of books: " + expected
                + " but was: " + actual);
    }
}
