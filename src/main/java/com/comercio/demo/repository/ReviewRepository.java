package com.comercio.demo.repository;


import com.comercio.demo.entity.Customer;
import com.comercio.demo.entity.Product;
import com.comercio.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    boolean existsByCustomerAndProduct(Customer customer,Product product);

}
