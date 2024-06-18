package com.example.movieblog.application.usecases.category;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.domain.entities.Category;

public class CreateCategoryInteractor {
    private final CategoryGateway categoryGateway;

    public CreateCategoryInteractor(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category createCategory(Category category){
        return categoryGateway.createCategory(category);
    }

}
