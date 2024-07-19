package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.book.BookDto;
import com.application.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.application.bookstore.dto.book.CreateBookRequestDto;
import com.application.bookstore.model.Book;
import com.application.bookstore.model.Category;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toBook(CreateBookRequestDto bookRequestDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoriesIds(@MappingTarget BookDto bookDto, Book book) {
        Set<Long> categories = book.getCategories().stream()
                .map(Category::getId).collect(Collectors.toSet());
        bookDto.setCategoryIds(categories);
    }
}
