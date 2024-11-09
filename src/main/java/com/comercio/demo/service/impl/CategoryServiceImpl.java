package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Category;
import com.comercio.demo.repository.CategoryRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CategoryServiceImpl extends CrudServiceImpl<Category,Long> implements ICategoryService  {

    private final CategoryRepository categoryRepository;

    @Override
    protected RepoGeneric<Category, Long> getRepo() {
        return categoryRepository;
    }

}
