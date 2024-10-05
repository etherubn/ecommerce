package com.comercio.demo.repository;


import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByNameAndCategory(String name, Category category);
    List<Product> findByName(String name);
    List<Product> findByCategory(Category category);
}
