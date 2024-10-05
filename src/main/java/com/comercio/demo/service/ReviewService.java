package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseReviewDto;

import java.util.List;

public interface ReviewService {
    List<ResponseReviewDto> findAll();
    ResponseReviewDto create(CreateReviewDto createReviewDto);
    ResponseReviewDto update(Long id,CreateReviewDto createReviewDto);
    void delete(Long id);
    ResponseReviewDto getById(Long id);
}
