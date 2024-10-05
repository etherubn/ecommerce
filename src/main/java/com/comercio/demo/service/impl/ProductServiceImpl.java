package com.comercio.demo.service.impl;

import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.dto.response.ResponseCategoryDto;
import com.comercio.demo.dto.response.ResponseProductDto;
import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Product;
import com.comercio.demo.handlerException.IdWrongValueException;
import com.comercio.demo.handlerException.NotFoundException;
import com.comercio.demo.handlerException.RepeatedException;
import com.comercio.demo.repository.CategoryRepository;
import com.comercio.demo.repository.ProductRepository;
import com.comercio.demo.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseProductDto> findAll() {

        return productRepository.findAll().stream().map(
                product -> {
                    ResponseProductDto responseProductDto = modelMapper.map(product, ResponseProductDto.class);
                    if (product.getCategory()!=null){
                        ResponseCategoryDto responseCategoryDto = modelMapper.map(product.getCategory(),ResponseCategoryDto.class);
                        responseProductDto.setResponseCategoryDto(responseCategoryDto);
                    }
                    return responseProductDto;
                }).toList();
    }

    @Transactional
    @Override
    public ResponseProductDto create(CreateProductDto createProductDto) {
        Category category = categoryRepository.findById(createProductDto.getCategory()).orElseThrow(()->new NotFoundException("Category",createProductDto.getCategory()));

        if (productRepository.existsByNameAndCategory(createProductDto.getName(),category)){
            throw new RepeatedException("Product");
        }
        modelMapper.typeMap(CreateProductDto.class, Product.class).addMappings(mapper -> mapper.skip(Product::setId));
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setCategory(category);


        Product productSaved = productRepository.save(product);

        ResponseProductDto responseProductDto = modelMapper.map(productSaved, ResponseProductDto.class);
        ResponseCategoryDto responseCategoryDto = modelMapper.map(category, ResponseCategoryDto.class);
        responseProductDto.setResponseCategoryDto(responseCategoryDto);
        return responseProductDto;
    }

    @Override
    public ResponseProductDto update(Long id, CreateProductDto createProductDto) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Product",id);
        }

        Category category = categoryRepository.findById(createProductDto.getCategory()).orElseThrow(()-> new NotFoundException("Category",id));

        String existProduct = usedNameAndCategory(createProductDto.getName(),category,id);

//        if (!existProduct.isEmpty()){
//
//        }
        Product product = modelMapper.map(createProductDto, Product.class);
        product.setId(id);


        Product productUpdated = productRepository.save(product);
        ResponseProductDto responseProductDto = modelMapper.map(productUpdated, ResponseProductDto.class);
        ResponseCategoryDto responseCategoryDto = modelMapper.map(category,ResponseCategoryDto.class);

        responseProductDto.setResponseCategoryDto(responseCategoryDto);

        return responseProductDto;
    }

    @Override
    public void delete(Long id) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Product",id);
        }

        Product product = productRepository.findById(id).orElseThrow(()-> new NotFoundException("Product",id));
        Category category = categoryRepository.findById(product.getCategory().getId())
                        .orElseThrow(()-> new NotFoundException("Category",product.getCategory().getId()));

        productRepository.delete(product);
    }

    @Override
    public ResponseProductDto getById(Long id) {
        if (id==null || id<=0){
            throw new IdWrongValueException("Product",id);
        }
        Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("Product",id));
        ResponseProductDto responseProductDto = modelMapper.map(product, ResponseProductDto.class);
        ResponseCategoryDto responseCategoryDto = modelMapper.map(product.getCategory(), ResponseCategoryDto.class);

        responseProductDto.setResponseCategoryDto(responseCategoryDto);

        return responseProductDto;
    }

    private String usedNameAndCategory(String name,Category category,Long idInitial){
        String error= "";

        boolean existName = productRepository.findByName(name).stream()
                .anyMatch(product -> !product.getId().equals(idInitial));

        if (existName){
            error+= "name ";
        }

        boolean existCategory = productRepository.findByCategory(category).stream()
                .anyMatch(product -> !product.getId().equals(idInitial));

        if (existCategory){
            error+="category";
        }

        return error;
    }
}
