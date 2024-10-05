package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;

import java.util.List;

public interface CountryService {
    List<ResponseCountryDto> findAll();
    ResponseCountryDto create(CreateCountryDto createCountryDto);
    ResponseCountryDto update(Long id, CreateCountryDto createCountryDto);
    void delete(Long id);
    ResponseCountryDto getById(Long id);

}
