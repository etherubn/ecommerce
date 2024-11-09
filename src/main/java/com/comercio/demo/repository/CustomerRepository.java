package com.comercio.demo.repository;


import com.comercio.demo.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends RepoGeneric<Customer,Long> {
//    boolean existsByDni(String dni);
//    boolean existsByEmail(String email);
//    boolean existsByUsername(String username);
//    Optional<Customer> findByEmail(String email);
//    Optional<Customer> findByUsername(String username);
//    Optional<Customer> findByDni(String email);


}
