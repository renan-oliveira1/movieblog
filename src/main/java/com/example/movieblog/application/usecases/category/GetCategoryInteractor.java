package com.example.movieblog.application.usecases.category;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.domain.entities.Category;

import java.util.Optional;

public class GetCategoryInteractor {
    private final CategoryGateway categoryGateway;

    public GetCategoryInteractor(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Optional<Category> getCategory(Long id){
        return categoryGateway.findOne(id);
    }
}
