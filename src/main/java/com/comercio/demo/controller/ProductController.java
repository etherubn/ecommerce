package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.dto.response.ResponseProductDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Product;
import com.comercio.demo.service.ICategoryService;
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
@RequestMapping("/api/v1")
public class ProductController {
    private final IProductService productService;
    private final ICategoryService categoryService;
    private final MapperUtil mapperUtil;

    @GetMapping("/products")
    public ResponseEntity<List<ResponseProductDto>> getAll() {
        List<ResponseProductDto> productDtos = mapperUtil.mapList(productService.findAll(), ResponseProductDto.class);
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ResponseProductDto> create(@Valid @RequestBody CreateProductDto createProductDto) {
        Category category = categoryService.getById(createProductDto.getIdCategory());
        Product product = productService.create(mapperUtil.map(createProductDto, Product.class));


        product.setCategory(category);

        return new ResponseEntity<>(mapperUtil.map(product,ResponseProductDto.class),HttpStatus.CREATED);
    }


    @PutMapping("products/{id}")
    public ResponseEntity<ResponseProductDto> update(@PathVariable Long id, @Valid @RequestBody CreateProductDto createProductDto) {
        Category category = categoryService.getById(createProductDto.getIdCategory());
        createProductDto.setIdProduct(id);
        Product product =  productService.update(id,mapperUtil.map(createProductDto, Product.class));
        product.setCategory(category);
        return new ResponseEntity<>(mapperUtil.map(product,ResponseProductDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ResponseProductDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(productService.getById(id),ResponseProductDto.class),HttpStatus.OK);
    }
}
