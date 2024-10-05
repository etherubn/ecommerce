package com.comercio.demo.repository;


import com.comercio.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByDni(String dni);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    List<Customer> findByEmail(String email);
    List<Customer> findByUsername(String username);
    List<Customer> findByDni(String email);



}
