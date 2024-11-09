package com.comercio.demo.repository;


import com.comercio.demo.entity.OrderedProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends RepoGeneric<OrderedProduct,Long> {
}
