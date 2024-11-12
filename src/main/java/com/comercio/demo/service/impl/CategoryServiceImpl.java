package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.repository.CategoryRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICategoryService;
import com.comercio.demo.util.MapperUtil;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category,Long, CreateCategoryDto> implements ICategoryService  {

    private final  CategoryRepository categoryRepository;

    public CategoryServiceImpl(MapperUtil mapperUtil, CategoryRepository categoryRepository) {
        super(mapperUtil);
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected RepoGeneric<Category, Long> getRepo() {
        return categoryRepository;
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    @Override
    protected Class<CreateCategoryDto> getDtoClass() {
        return CreateCategoryDto.class;
    }

    @Override
    protected void setEntityId(Category entity, Long id) {
        entity.setIdCategory(id);
    }
}
