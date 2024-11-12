package com.comercio.demo.service.impl;


import com.comercio.demo.dto.request.CreateProductDto;
import com.comercio.demo.entity.Product;
import com.comercio.demo.repository.ProductRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IProductService;
import com.comercio.demo.util.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
public class ProductServiceImpl extends CrudServiceImpl<Product,Long, CreateProductDto> implements IProductService  {

    private final ProductRepository productRepository;

    public ProductServiceImpl(MapperUtil mapperUtil, ProductRepository productRepository) {
        super(mapperUtil);
        this.productRepository = productRepository;
    }

    @Override
    protected RepoGeneric<Product, Long> getRepo() {
        return productRepository;
    }

    @Override
    protected Class<Product> getEntityClass() {
        return Product.class;
    }

    @Override
    protected Class<CreateProductDto> getDtoClass() {
        return CreateProductDto.class;
    }

    @Override
    protected void setEntityId(Product entity, Long id) {
        entity.setIdProduct(id);
    }
}
