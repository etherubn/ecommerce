package com.comercio.demo.service;

import java.util.List;

public interface ICrudService<T,ID,Dto> {
    List<Dto> findAll();
    Dto create(Dto dto);
    Dto update(ID id, Dto dto);
    void delete(ID id);
    Dto getById(ID id);
}
