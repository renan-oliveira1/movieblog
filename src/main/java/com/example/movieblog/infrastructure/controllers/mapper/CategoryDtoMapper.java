package com.example.movieblog.infrastructure.controllers.mapper;

import com.example.movieblog.domain.entities.Category;
import com.example.movieblog.infrastructure.controllers.requests.CategoryRequest;
import com.example.movieblog.infrastructure.controllers.responses.CategoryResponse;

public class CategoryDtoMapper {
    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.id(), category.name(), category.description());
    }

    public Category toCategory(CategoryRequest categoryRequest){
        return new Category(categoryRequest.name(), categoryRequest.description());
    }
}
