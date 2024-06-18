package com.example.movieblog.application.usecases.category;

import com.example.movieblog.application.gateways.CategoryGateway;
import com.example.movieblog.domain.entities.Category;

import java.util.List;

public class GetAllCategoriesInteractor {
    private final CategoryGateway categoryGateway;

    public GetAllCategoriesInteractor(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public List<Category> getAllCategories(){
        return categoryGateway.findAll();
    }
}
