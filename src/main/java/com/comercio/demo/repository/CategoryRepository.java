package com.comercio.demo.repository;

import com.comercio.demo.entity.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends RepoGeneric<Category,Long> {
//    boolean existsByTipo(CategoryType categoryType);
}
