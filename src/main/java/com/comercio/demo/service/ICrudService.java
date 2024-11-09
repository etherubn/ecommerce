package com.comercio.demo.service;

import java.util.List;

public interface ICrudService<T,ID> {
    List<T> findAll();
    T create(T t);
    T update(ID id, T t);
    void delete(ID id);
    T getById(ID id);
}
