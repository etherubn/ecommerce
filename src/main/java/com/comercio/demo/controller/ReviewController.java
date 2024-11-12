package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseReviewDto;
import com.comercio.demo.service.IReviewService;
import com.comercio.demo.util.MapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final IReviewService reviewService;
    private final MapperUtil mapperUtil;

    @GetMapping
    public ResponseEntity<List<ResponseReviewDto>> getAll() {
        List<ResponseReviewDto> reviewDtos = mapperUtil.mapList(reviewService.findAll(), ResponseReviewDto.class);
        return new ResponseEntity<>(reviewDtos,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseReviewDto> create(@Valid @RequestBody CreateReviewDto createReviewDto) {
        CreateReviewDto reviewDto = reviewService.create(createReviewDto);
        return new ResponseEntity<>(mapperUtil.map(reviewDto,ResponseReviewDto.class),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseReviewDto> update(@PathVariable Long id, @Valid @RequestBody CreateReviewDto createReviewDto) {
        CreateReviewDto reviewDto =  reviewService.update(id,createReviewDto);
        return new ResponseEntity<>(mapperUtil.map(reviewDto,ResponseReviewDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseReviewDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(reviewService.getById(id),ResponseReviewDto.class),HttpStatus.OK);
    }
}
