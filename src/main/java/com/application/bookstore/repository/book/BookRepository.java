package com.application.bookstore.repository.book;

import com.application.bookstore.model.Book;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.categories")
    Page<Book> findAll(Pageable pageable);

    @Query("SELECT b FROM Book b JOIN FETCH b.categories c WHERE c.id = :categoryId")
    List<Book> findAllByCategoryId(Long categoryId);
}
