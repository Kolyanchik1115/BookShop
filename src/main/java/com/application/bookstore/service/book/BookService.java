package com.application.bookstore.service.book;

import com.application.bookstore.dto.book.BookDto;
import com.application.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.application.bookstore.dto.book.CreateBookRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {

    BookDto save(CreateBookRequestDto requestDto);

    BookDto findById(Long id);

    List<BookDto> findAll(Pageable pageable);

    void deleteBookById(Long id);

    BookDto updateBookById(Long id, CreateBookRequestDto bookDto);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
