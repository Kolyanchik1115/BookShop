package com.application.bookstore.service.category;

import com.application.bookstore.dto.category.CategoryResponseDto;
import com.application.bookstore.dto.category.CreateCategoryRequestDto;
import com.application.bookstore.exception.EntityNotFoundException;
import com.application.bookstore.mapper.CategoryMapper;
import com.application.bookstore.model.Category;
import com.application.bookstore.repository.category.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find category by id: " + id)
        );
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryResponseDto save(CreateCategoryRequestDto requestDto) {
        Category category = categoryMapper.toCategory(requestDto);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryResponseDto update(Long id, CreateCategoryRequestDto requestDto) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't find category by id: " + id);
        }
        Category category = categoryMapper.toCategory(requestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
