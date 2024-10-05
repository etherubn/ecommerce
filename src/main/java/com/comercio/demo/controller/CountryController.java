package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCategoryDto;
import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.service.CategoryService;
import com.comercio.demo.service.CountryService;
import com.comercio.demo.service.impl.CategoryServiceImpl;
import com.comercio.demo.service.impl.CountryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController implements BaseController<CreateCountryDto, ResponseCountryDto>{
    private final CountryService countryService;

    public CountryController(CountryServiceImpl categoryServiceImpl) {
        this.countryService = categoryServiceImpl;
    }


    @GetMapping("/countries")
    @Override
    public ResponseEntity<List<ResponseCountryDto>> getAll() {
        return new ResponseEntity<>(countryService.findAll(),HttpStatus.OK);
    }

    @PostMapping("/countries")
    @Override
    public ResponseEntity<ResponseCountryDto> create(@Valid @RequestBody CreateCountryDto createCountryDto) {
        return new ResponseEntity<>(countryService.create(createCountryDto),HttpStatus.CREATED);
    }

    @PutMapping("/countries/{id}")
    @Override
    public ResponseEntity<ResponseCountryDto> update(@PathVariable Long id, @Valid @RequestBody CreateCountryDto createCountryDto) {

        return new ResponseEntity<>(countryService.update(id,createCountryDto),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/countries/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/countries/{id}")
    @Override
    public ResponseEntity<ResponseCountryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(countryService.getById(id),HttpStatus.OK);
    }
}
