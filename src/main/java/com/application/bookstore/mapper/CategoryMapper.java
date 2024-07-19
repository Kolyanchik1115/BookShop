package com.application.bookstore.mapper;

import com.application.bookstore.config.MapperConfig;
import com.application.bookstore.dto.category.CategoryResponseDto;
import com.application.bookstore.dto.category.CreateCategoryRequestDto;
import com.application.bookstore.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toCategory(CreateCategoryRequestDto requestDto);
}
