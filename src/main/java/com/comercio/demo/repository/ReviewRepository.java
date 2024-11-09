package com.comercio.demo.repository;



import com.comercio.demo.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends RepoGeneric<Review,Long> {
//    boolean existsByCustomerAndProduct(Customer customer,Product product);
//    List<Review> findAllByProductId(Long id);

}
