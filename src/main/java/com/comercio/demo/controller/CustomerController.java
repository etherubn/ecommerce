package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;
import com.comercio.demo.entity.Country;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.service.ICountryService;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//falta arreglar el actualizar
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CustomerController {
    private final ICustomerService customerService;
    private final ICountryService countryService;
    private final MapperUtil mapperUtil;

    @GetMapping("/customers")
    public ResponseEntity<List<ResponseCustomerDto>> getAll() {
        List<ResponseCustomerDto> customerDtos = mapperUtil.mapList(customerService.findAll(), ResponseCustomerDto.class);
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }

    @PostMapping("/customers")
    public ResponseEntity<ResponseCustomerDto> create(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        Country country =countryService.getById(createCustomerDto.getIdCountry());
        Customer customer = customerService.create(mapperUtil.map(createCustomerDto, Customer.class));
        customer.setCountry(country);
        return new ResponseEntity<>(mapperUtil.map(customer,ResponseCustomerDto.class),HttpStatus.CREATED);
    }


    @PutMapping("customers/{id}")
    public ResponseEntity<ResponseCustomerDto> update(@PathVariable Long id, @Valid @RequestBody CreateCustomerDto createCustomerDto) {
        createCustomerDto.setIdCustomer(id);
        Customer customer =  customerService.update(id,mapperUtil.map(createCustomerDto, Customer.class));
        return new ResponseEntity<>(mapperUtil.map(customer,ResponseCustomerDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<ResponseCustomerDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(customerService.getById(id),ResponseCustomerDto.class),HttpStatus.OK);
    }
}
