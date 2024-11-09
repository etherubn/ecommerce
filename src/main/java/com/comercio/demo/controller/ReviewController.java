package com.comercio.demo.controller;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseReviewDto;
import com.comercio.demo.entity.Review;
import com.comercio.demo.service.ICustomerService;
import com.comercio.demo.service.IProductService;
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
@RequestMapping("/api/v1")
public class ReviewController {
    private final IReviewService reviewService;
    private final MapperUtil mapperUtil;
    private final IProductService productService;
    private final ICustomerService customerService;

    @GetMapping("/reviews")
    public ResponseEntity<List<ResponseReviewDto>> getAll() {
        List<ResponseReviewDto> reviewDtos = mapperUtil.mapList(reviewService.findAll(), ResponseReviewDto.class);
        return new ResponseEntity<>(reviewDtos,HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ResponseReviewDto> create(@Valid @RequestBody CreateReviewDto createReviewDto) {
        customerService.getById(createReviewDto.getIdCustomer());
        customerService.getById(createReviewDto.getIdProduct());

        Review review = reviewService.create(mapperUtil.map(createReviewDto, Review.class));
        System.out.println(review);

        return new ResponseEntity<>(mapperUtil.map(review,ResponseReviewDto.class),HttpStatus.CREATED);
    }


    @PutMapping("reviews/{id}")
    public ResponseEntity<ResponseReviewDto> update(@PathVariable Long id, @Valid @RequestBody CreateReviewDto createReviewDto) {
        createReviewDto.setIdReview(id);
        Review review =  reviewService.update(id,mapperUtil.map(createReviewDto, Review.class));
        return new ResponseEntity<>(mapperUtil.map(review,ResponseReviewDto.class),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("reviews/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reviewService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("reviews/{id}")
    public ResponseEntity<ResponseReviewDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(mapperUtil.map(reviewService.getById(id),ResponseReviewDto.class),HttpStatus.OK);
    }
}
