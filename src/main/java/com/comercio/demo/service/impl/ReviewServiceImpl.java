package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Review;
import com.comercio.demo.repository.ReviewRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReviewServiceImpl extends CrudServiceImpl<Review,Long> implements IReviewService  {

    private final ReviewRepository reviewRepository;

    @Override
    protected RepoGeneric<Review, Long> getRepo() {
        return reviewRepository;
    }

}
