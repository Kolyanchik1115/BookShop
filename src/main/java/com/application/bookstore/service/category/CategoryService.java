package com.application.bookstore.service.category;

import com.application.bookstore.dto.category.CategoryResponseDto;
import com.application.bookstore.dto.category.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    List<CategoryResponseDto> findAll(Pageable pageable);

    CategoryResponseDto getById(Long id);

    CategoryResponseDto save(CreateCategoryRequestDto requestDto);

    CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);

}
