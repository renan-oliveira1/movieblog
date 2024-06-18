package com.example.movieblog.application.usecases.category;

import com.example.movieblog.application.gateways.CategoryGateway;

public class DeleteCategoryInteractor {
    private final CategoryGateway categoryGateway;

    public DeleteCategoryInteractor(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void deleteCategory(Long id){
        deleteCategory(id);
    }
}
