package com.example.movieblog.infrastructure.persistence.repositories;

import com.example.movieblog.infrastructure.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
