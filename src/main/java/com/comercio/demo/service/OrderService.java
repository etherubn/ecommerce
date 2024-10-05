package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.request.CreateOrderDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.dto.response.ResponseOrderDto;

import java.util.List;

public interface OrderService {
    List<ResponseOrderDto> findAll();
    ResponseOrderDto create(CreateOrderDto createOrderDto);
    ResponseOrderDto getById(Long id);
    void cancelOrder(Long id);
    void deleteOrder(Long id);

}
