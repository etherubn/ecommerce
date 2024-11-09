package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.entity.Category;

import java.util.List;

public interface ICategoryService extends ICrudService<Category,Long> {

}
