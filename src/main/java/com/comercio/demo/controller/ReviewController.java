package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseReviewDto;
import com.comercio.demo.service.ReviewService;
import com.comercio.demo.service.impl.ReviewServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController implements BaseController<CreateReviewDto, ResponseReviewDto>{

    private final ReviewService reviewService;

    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Override
    public ResponseEntity<List<ResponseReviewDto>> getAll() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @Override
    public ResponseEntity<ResponseReviewDto> create(@Valid @RequestBody CreateReviewDto createReviewDto) {
        return new ResponseEntity<>(reviewService.create(createReviewDto),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ResponseReviewDto> update(Long id, CreateReviewDto createReviewDto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<ResponseReviewDto> getById(Long id) {
        return null;
    }
}
