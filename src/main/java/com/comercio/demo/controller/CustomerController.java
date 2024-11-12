package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCustomerDto;
import com.comercio.demo.dto.response.ResponseCustomerDto;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseCustomerDto>> getAll() {
        List<ResponseCustomerDto> customerDtos = mapperUtil.mapList(customerService.findAll(), ResponseCustomerDto.class);
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseCustomerDto> create(@Valid @RequestBody CreateCustomerDto createCustomerDto) {
        CreateCustomerDto customerDto = customerService.create(createCustomerDto);
        return new ResponseEntity<>(mapperUtil.map(customerDto,ResponseCustomerDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomerDto> update(@PathVariable Long id, @Valid @RequestBody CreateCustomerDto createCustomerDto) {
        CreateCustomerDto customerDto =  customerService.update(id,createCustomerDto);
        return new ResponseEntity<>(mapperUtil.map(customerDto,ResponseCustomerDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomerDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(customerService.getById(id),ResponseCustomerDto.class),HttpStatus.OK);
    }
}
