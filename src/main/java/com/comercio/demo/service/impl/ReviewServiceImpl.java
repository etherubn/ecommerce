package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.entity.Review;
import com.comercio.demo.repository.ReviewRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IReviewService;
import com.comercio.demo.util.MapperUtil;
import org.springframework.stereotype.Service;


@Service
public class ReviewServiceImpl extends CrudServiceImpl<Review,Long, CreateReviewDto> implements IReviewService  {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(MapperUtil mapperUtil, ReviewRepository reviewRepository) {
        super(mapperUtil);
        this.reviewRepository = reviewRepository;
    }

    @Override
    protected RepoGeneric<Review, Long> getRepo() {
        return reviewRepository;
    }

    @Override
    protected Class<Review> getEntityClass() {
        return Review.class;
    }

    @Override
    protected Class<CreateReviewDto> getDtoClass() {
        return CreateReviewDto.class;
    }

    @Override
    protected void setEntityId(Review entity, Long id) {
        entity.setIdReview(id);
    }
}
