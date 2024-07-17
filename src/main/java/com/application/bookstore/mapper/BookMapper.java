package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.book.BookDto;
import com.application.bookstore.dto.book.CreateBookRequestDto;
import com.application.bookstore.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toBook(CreateBookRequestDto bookRequestDto);
}
