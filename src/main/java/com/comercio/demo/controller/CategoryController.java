package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.service.ICategoryService;
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
public class CategoryController{
    private final ICategoryService categoryService;
    private final MapperUtil mapperUtil;

    @GetMapping("/categories")
    public ResponseEntity<List<ResponseCategoryDto>> getAll() {
        List<ResponseCategoryDto> categoryDtos = mapperUtil.mapList(categoryService.findAll(), ResponseCategoryDto.class);
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseCategoryDto> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        CreateCategoryDto categoryDto = categoryService.create(createCategoryDto);
        return new ResponseEntity<>(mapperUtil.map(categoryDto,ResponseCategoryDto.class),HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<ResponseCategoryDto> update(@PathVariable Long id, @Valid @RequestBody CreateCategoryDto createCategoryDto) {
        CreateCategoryDto categoryDto =  categoryService.update(id,createCategoryDto);
        return new ResponseEntity<>(mapperUtil.map(categoryDto,ResponseCategoryDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("categories/{id}")
    public ResponseEntity<ResponseCategoryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(categoryService.getById(id),ResponseCategoryDto.class),HttpStatus.OK);
    }
}
