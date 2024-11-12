package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.dto.response.ResponseProductDto;
import com.comercio.demo.service.IProductService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final IProductService productService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> getAll() {
        List<ResponseProductDto> productDtos = mapperUtil.mapList(productService.findAll(), ResponseProductDto.class);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseProductDto> create(@Valid @RequestBody CreateProductDto createProductDto) {
        CreateProductDto productDto = productService.create(createProductDto);
        return new ResponseEntity<>(mapperUtil.map(productDto,ResponseProductDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDto> update(@PathVariable Long id, @Valid @RequestBody CreateProductDto createProductDto) {
        CreateProductDto productDto =  productService.update(id,createProductDto);
        return new ResponseEntity<>(mapperUtil.map(productDto,ResponseProductDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseProductDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(productService.getById(id),ResponseProductDto.class),HttpStatus.OK);
    }
}
