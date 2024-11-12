package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.repository.CustomerRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl extends CrudServiceImpl<Customer,Long,CreateCustomerDto> implements ICustomerService  {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(MapperUtil mapperUtil, CustomerRepository customerRepository) {
        super(mapperUtil);
        this.customerRepository = customerRepository;
    }

    @Override
    protected RepoGeneric<Customer, Long> getRepo() {
        return customerRepository;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    protected Class getDtoClass() {
        return CreateCustomerDto.class;
    }


}
