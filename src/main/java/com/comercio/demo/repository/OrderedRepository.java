package com.comercio.demo.repository;


import com.comercio.demo.entity.Ordered;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderedRepository extends RepoGeneric<Ordered,Long> {
//    List<Ordered> findAllByCustomerId(Long id);
}
