package com.comercio.demo.repository;

import com.comercio.demo.entity.Category;
import com.comercio.demo.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByTipo(CategoryType categoryType);
}
