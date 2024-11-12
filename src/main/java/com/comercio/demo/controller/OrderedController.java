package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateOrderedDto;
import com.comercio.demo.dto.response.ResponseOrderedDto;
import com.comercio.demo.service.IOrderedService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ordereds")
public class OrderedController {
    private final IOrderedService orderedService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseOrderedDto>> getAll() {
        List<ResponseOrderedDto> orderedDtos = mapperUtil.mapList(orderedService.findAll(), ResponseOrderedDto.class);
        return new ResponseEntity<>(orderedDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseOrderedDto> create(@Valid @RequestBody CreateOrderedDto createOrderedDto) {
        CreateOrderedDto orderedDto = orderedService.create(createOrderedDto);
        return new ResponseEntity<>(mapperUtil.map(orderedDto,ResponseOrderedDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseOrderedDto> update(@PathVariable Long id, @Valid @RequestBody CreateOrderedDto createOrderedDto) {
        CreateOrderedDto orderedDto =  orderedService.update(id,createOrderedDto);
        return new ResponseEntity<>(mapperUtil.map(orderedDto,ResponseOrderedDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderedService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderedDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(orderedService.getById(id),ResponseOrderedDto.class),HttpStatus.OK);
    }
}
