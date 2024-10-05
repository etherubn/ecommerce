package com.comercio.demo.repository;


import com.comercio.demo.entity.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Long> {
}