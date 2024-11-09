package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateCountryDto;
import com.comercio.demo.dto.response.ResponseCountryDto;
import com.comercio.demo.entity.Country;
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
@RequestMapping("/api/v1")
public class CountryController {
    private final ICountryService countryService;
    private final MapperUtil mapperUtil;

    @GetMapping("/countries")
    public ResponseEntity<List<ResponseCountryDto>> getAll() {
        List<ResponseCountryDto> countryDtos = mapperUtil.mapList(countryService.findAll(), ResponseCountryDto.class);
        return new ResponseEntity<>(countryDtos,HttpStatus.OK);
    }

    @PostMapping("/countries")
    public ResponseEntity<ResponseCountryDto> create(@Valid @RequestBody CreateCountryDto createCountryDto) {
        Country country = countryService.create(mapperUtil.map(createCountryDto, Country.class));

        return new ResponseEntity<>(mapperUtil.map(country,ResponseCountryDto.class),HttpStatus.CREATED);
    }


    @PutMapping("countries/{id}")
    public ResponseEntity<ResponseCountryDto> update(@PathVariable Long id, @Valid @RequestBody CreateCountryDto createCountryDto) {
        createCountryDto.setIdCountry(id);
        Country country =  countryService.update(id,mapperUtil.map(createCountryDto, Country.class));
        return new ResponseEntity<>(mapperUtil.map(country,ResponseCountryDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("countries/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("countries/{id}")
    public ResponseEntity<ResponseCountryDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(countryService.getById(id),ResponseCountryDto.class),HttpStatus.OK);
    }
}
