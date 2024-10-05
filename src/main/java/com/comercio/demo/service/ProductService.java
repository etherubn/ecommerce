package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.dto.response.ResponseProductDto;

import java.util.List;

public interface ProductService {
    List<ResponseProductDto> findAll();
    ResponseProductDto create(CreateProductDto createProductDto);
    ResponseProductDto update(Long id, CreateProductDto createProductDto);
    void delete(Long id);
    ResponseProductDto getById(Long id);

}
