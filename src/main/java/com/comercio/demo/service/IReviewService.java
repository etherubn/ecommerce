package com.comercio.demo.service;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.ResponseReviewDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Review;

import java.util.List;

public interface IReviewService extends ICrudService<Review,Long> {

}
