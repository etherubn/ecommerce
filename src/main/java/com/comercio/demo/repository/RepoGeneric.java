package com.comercio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepoGeneric<T,D> extends JpaRepository<T,D> {
}
