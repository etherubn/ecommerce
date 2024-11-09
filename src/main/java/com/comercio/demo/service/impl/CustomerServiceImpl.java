package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Customer;
import com.comercio.demo.repository.CustomerRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl extends CrudServiceImpl<Customer,Long> implements ICustomerService  {

    private final CustomerRepository customerRepository;

    @Override
    protected RepoGeneric<Customer, Long> getRepo() {
        return customerRepository;
    }

}
