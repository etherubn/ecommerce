package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.service.CategoryService;
import com.comercio.demo.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController implements BaseController<CreateCategoryDto, ResponseCategoryDto>{
    private final CategoryService categoryService;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryService = categoryServiceImpl;
    }


    @GetMapping("/categories")
    @Override
    public ResponseEntity<List<ResponseCategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/categories")
    @Override
    public ResponseEntity<ResponseCategoryDto> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        return new ResponseEntity<>(categoryService.create(createCategoryDto),HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    @Override
    public ResponseEntity<ResponseCategoryDto> update(@PathVariable Long id, @Valid @RequestBody CreateCategoryDto createCategoryDto) {

        return new ResponseEntity<>(categoryService.update(id,createCategoryDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("categories/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("categories/{id}")
    @Override
    public ResponseEntity<ResponseCategoryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryService.getById(id),HttpStatus.OK);
    }
}
