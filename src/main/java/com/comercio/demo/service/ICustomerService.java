package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Customer;

import java.util.List;

public interface ICustomerService extends ICrudService<Customer,Long> {

}
