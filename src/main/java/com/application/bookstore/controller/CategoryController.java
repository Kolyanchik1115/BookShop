package com.application.bookstore.controller;

import com.application.bookstore.dto.book.BookDtoWithoutCategoryIds;
import com.application.bookstore.dto.category.CategoryResponseDto;
import com.application.bookstore.dto.category.CreateCategoryRequestDto;
import com.application.bookstore.service.book.BookService;
import com.application.bookstore.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/categories")
@Tag(name = "Category management", description = "Endpoints for managing categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final BookService bookService;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Create category", description = "Create a new category")
    public CategoryResponseDto createCategory(@RequestBody @Valid
                                                  CreateCategoryRequestDto requestDto) {
        return categoryService.save(requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @Operation(summary = "Get categories", description = "Get all categories")
    public List<CategoryResponseDto> getAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "Get category", description = "Get category by id")
    public CategoryResponseDto getCategoryById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update category", description = "Update category by id")
    public CategoryResponseDto updateCategoryById(
            @PathVariable Long id,
            @RequestBody @Valid CreateCategoryRequestDto requestDto
    ) {
        return categoryService.update(id, requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete category", description = "Delete category by id")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}/books")
    @Operation(summary = "Get books", description = "Get books by category id")
    public List<BookDtoWithoutCategoryIds> getBooksByCategoryId(@PathVariable Long id) {
        return bookService.getBooksByCategoryId(id);
    }
}

