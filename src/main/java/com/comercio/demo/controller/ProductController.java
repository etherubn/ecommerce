package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.dto.response.ResponseProductDto;
import com.comercio.demo.service.ProductService;
import com.comercio.demo.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController implements BaseController<CreateProductDto, ResponseProductDto>{
    private ProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> getAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseProductDto> create(@RequestBody @Valid CreateProductDto createProductDto) {
        return new ResponseEntity<>(productService.create(createProductDto),HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProductDto> update(@PathVariable Long id, @RequestBody @Valid CreateProductDto createProductDto) {
        return new ResponseEntity<>(productService.update(id,createProductDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<ResponseProductDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }
}
