package com.comercio.demo.repository;


import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends RepoGeneric<Product,Long> {

//    boolean existsByNameAndCategory(String name, Category category);
//    List<Product> findByName(String name);
//    List<Product> findByCategory(Category category);
}
