package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;

import com.comercio.demo.service.CustomerService;
import com.comercio.demo.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController implements BaseController<CreateCustomerDto, ResponseCustomerDto>{

    private final CustomerService customerService;


    public CustomerController(CustomerServiceImpl customerService) {
        this.customerService = customerService;

    }

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseCustomerDto>> getAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseCustomerDto> create(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        return new ResponseEntity<>(customerService.create(createCustomerDto),HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomerDto> update(@PathVariable Long id, @Valid @RequestBody  CreateCustomerDto createCustomerDto) {
        return new ResponseEntity<>(customerService.update(id,createCustomerDto),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomerDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getById(id),HttpStatus.OK);
    }
}
