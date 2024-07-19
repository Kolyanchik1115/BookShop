package com.application.bookstore.dto.book;

import java.util.Set;
import lombok.Data;

@Data
public class BookDto extends BookDtoWithoutCategoryIds {
    private Set<Long> categoryIds;
}
