package com.comercio.demo.service.impl;

import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICrudService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

abstract class CrudServiceImpl<T,ID> implements ICrudService<T,ID> {

    protected abstract RepoGeneric<T,ID> getRepo();

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Transactional
    @Override
    public T create(T t) {
        return getRepo().save(t);
    }

    @Transactional
    @Override
    public T update(ID id, T t) {
        Class<?> clazz = t.getClass();
        getRepo().findById(id).orElseThrow(()-> new NotFoundException("El valor de"+clazz.getSimpleName().toLowerCase()));
        return getRepo().save(t);
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(()-> new NotFoundException("El valor del id "+id));
        getRepo().deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public T getById(ID id) {
        return getRepo().findById(id).orElseThrow(()-> new NotFoundException("El valor del id "+id));
    }
}
