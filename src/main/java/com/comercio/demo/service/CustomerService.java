package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;

import java.util.List;

public interface CustomerService {
    List<ResponseCustomerDto> findAll();
    ResponseCustomerDto create(CreateCustomerDto createCustomerDto);
    ResponseCustomerDto update(Long id, CreateCustomerDto createCustomerDto);
    void delete(Long id);
    ResponseCustomerDto getById(Long id);

}
