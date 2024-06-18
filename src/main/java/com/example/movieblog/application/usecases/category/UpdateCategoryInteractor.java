package com.example.movieblog.application.usecases.category;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.domain.entities.Category;

import java.util.Optional;

public class UpdateCategoryInteractor {
    private final CategoryGateway categoryGateway;

    public UpdateCategoryInteractor(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Optional<Category> updateCategory(Long id, Category categoryUpdate){
        Optional<Category> categoryFromDb = categoryGateway.findOne(id);
        if(categoryFromDb.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(categoryGateway.update(categoryUpdate));
    }
}
