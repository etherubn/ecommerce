package com.comercio.demo.service.impl;

import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICrudService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
abstract class CrudServiceImpl<T,ID,Dto> implements ICrudService<T,ID,Dto> {

    protected abstract RepoGeneric<T,ID> getRepo();
    private final MapperUtil mapperUtil;



    protected abstract Class<T> getEntityClass();
    protected abstract Class<Dto> getDtoClass();
    protected void setEntityId(T entity, ID id) {}

    @Transactional(readOnly = true)
    @Override
    public List<Dto> findAll() {
        List<T> entities = getRepo().findAll();

        return mapperUtil.mapList(entities,getDtoClass());
    }

    @Transactional
    @Override
    public Dto create(Dto dto) {
        T entity = mapperUtil.map(dto, getEntityClass());
        T savedEntity = getRepo().save(entity);
        return mapperUtil.map(savedEntity, getDtoClass());
    }

    @Transactional
    @Override
    public Dto update(ID id, Dto dto) {
        getRepo().findById(id).orElseThrow(() -> new NotFoundException("No se encontró el recurso con id " + id));

        T entityToUpdate = mapperUtil.map(dto, getEntityClass());
        setEntityId(entityToUpdate, id);

        T updatedEntity = getRepo().save(entityToUpdate);
        return mapperUtil.map(updatedEntity, getDtoClass());
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new NotFoundException("No se encontró el recurso con id " + id));
        getRepo().deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Dto getById(ID id) {
        T entity = getRepo().findById(id)
                .orElseThrow(() -> new NotFoundException("No se encontró el recurso con id " + id));
        return mapperUtil.map(entity, getDtoClass());
    }
}
