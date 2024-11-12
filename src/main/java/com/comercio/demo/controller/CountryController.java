package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.service.ICountryService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final ICountryService countryService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseCountryDto>> getAll() {
        List<ResponseCountryDto> countryDtos = mapperUtil.mapList(countryService.findAll(), ResponseCountryDto.class);
        return new ResponseEntity<>(countryDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseCountryDto> create(@Valid @RequestBody CreateCountryDto createCountryDto) {
        CreateCountryDto countryDto = countryService.create(createCountryDto);
        return new ResponseEntity<>(mapperUtil.map(countryDto,ResponseCountryDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCountryDto> update(@PathVariable Long id, @Valid @RequestBody CreateCountryDto createCountryDto) {
        CreateCountryDto countryDto =  countryService.update(id,createCountryDto);
        return new ResponseEntity<>(mapperUtil.map(countryDto,ResponseCountryDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCountryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(countryService.getById(id),ResponseCountryDto.class),HttpStatus.OK);
    }
}
