package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;

import java.util.List;

public interface CategoryService {
    List<ResponseCategoryDto> findAll();
    ResponseCategoryDto create(CreateCategoryDto createCategoryDto);
    ResponseCategoryDto update(Long id, CreateCategoryDto createCategoryDto);
    void delete(Long id);
    ResponseCategoryDto getById(Long id);

}
