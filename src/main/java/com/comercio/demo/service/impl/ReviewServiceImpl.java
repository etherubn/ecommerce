package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateReviewDto;
import com.comercio.demo.dto.response.*;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.entity.Product;
import com.comercio.demo.entity.Review;
import com.comercio.demo.handlerException.IdWrongValueException;
import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.handlerException.RepeatedException;
import com.comercio.demo.repository.CustomerRepository;
import com.comercio.demo.repository.ProductRepository;
import com.comercio.demo.repository.ReviewRepository;
import com.comercio.demo.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ReviewServiceImpl(ReviewRepository reviewRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ResponseReviewDto> findAll() {
        return reviewRepository.findAll().stream().map(review -> {
            ResponseReviewDto responseReviewDto = modelMapper.map(review,ResponseReviewDto.class);
            ResponseCustomerDto responseCustomerDto = modelMapper.map(review.getCustomer(),ResponseCustomerDto.class);
            ResponseProductDto responseProductDto = modelMapper.map(review.getProduct(), ResponseProductDto.class);
            responseReviewDto.setCustomer(responseCustomerDto);
            responseReviewDto.setProduct(responseProductDto);
            return responseReviewDto;
        }).toList();
    }

    @Override
    public ResponseReviewDto create(CreateReviewDto createReviewDto) {
        Customer customer = customerRepository.findById(createReviewDto.getIdCustomer())
                .orElseThrow(()-> new NotFoundException("Customer",createReviewDto.getIdCustomer()));

        Product product = productRepository.findById(createReviewDto.getIdProduct())
                .orElseThrow(()-> new NotFoundException("Product",createReviewDto.getIdProduct()));


        if (reviewRepository.existsByCustomerAndProduct(customer,product)){
            throw new RepeatedException("Review");
        }


        Review review = new Review();
        review.setCustomer(customer);
        review.setProduct(product);
        review.setRating(createReviewDto.getRating());
        review.setComment(createReviewDto.getComment());

        Review reviewSaved = reviewRepository.save(review);

        ResponseReviewDto responseReviewDto = getResponseReviewDto(reviewSaved, product, customer);
        return responseReviewDto;
    }

    @Override
    public ResponseReviewDto update(Long id, CreateReviewDto createReviewDto) {
        if (id==null || id<0){
            throw new IdWrongValueException("Review",id);
        }

        Review review = reviewRepository.findById(id).orElseThrow(()->new NotFoundException("Review",id));
        Product product = productRepository.findById(createReviewDto.getIdProduct()).orElseThrow(()-> new NotFoundException("Product", createReviewDto.getIdProduct()));
        Customer customer = customerRepository.findById(createReviewDto.getIdCustomer()).orElseThrow(()-> new NotFoundException("Customer", createReviewDto.getIdCustomer()));

        review.setComment(createReviewDto.getComment());
        review.setRating(createReviewDto.getRating());

        reviewRepository.save(review);

        ResponseReviewDto responseReviewDto = getResponseReviewDto(review,product,customer);

        return responseReviewDto;
    }

    @Override
    public void delete(Long id) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Review",id);
        }

        Review review = reviewRepository.findById(id).orElseThrow(()-> new NotFoundException("Category",id));

        Customer customer = customerRepository.findById(review.getCustomer().getId())
                        .orElseThrow(()-> new NotFoundException("Customer",review.getCustomer().getId()));

        Product product = productRepository.findById(review.getProduct().getId())
                        .orElseThrow(()-> new NotFoundException("Product",review.getProduct().getId()));

        reviewRepository.delete(review);
    }

    @Override
    public ResponseReviewDto getById(Long id) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Review",id);
        }
        Review review = reviewRepository.findById(id).orElseThrow(()-> new NotFoundException("Review",id));


        return getResponseReviewDto(review,review.getProduct(),review.getCustomer());
    }

    private ResponseReviewDto getResponseReviewDto(Review review, Product product, Customer customer) {
        ResponseReviewDto responseReviewDto = modelMapper.map(review, ResponseReviewDto.class);
        ResponseProductDto responseProductDto = modelMapper.map(product,ResponseProductDto.class);
        ResponseCategoryDto responseCategoryDto = modelMapper.map(product.getCategory(),ResponseCategoryDto.class);
        responseProductDto.setResponseCategoryDto(responseCategoryDto);

        ResponseCustomerDto responseCustomerDto = modelMapper.map(customer,ResponseCustomerDto.class);
        ResponseCountryDto responseCountryDto = modelMapper.map(customer.getCountry(),ResponseCountryDto.class);
        responseCustomerDto.setResponseCountryDto(responseCountryDto);

        responseReviewDto.setProduct(responseProductDto);
        responseReviewDto.setCustomer(responseCustomerDto);
        return responseReviewDto;
    }
}
