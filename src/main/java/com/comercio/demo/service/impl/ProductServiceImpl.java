package com.comercio.demo.service.impl;


import com.comercio.demo.entity.Product;
import com.comercio.demo.repository.ProductRepository;
import com.comercio.demo.repository.RepoGeneric;
import com.comercio.demo.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ProductServiceImpl extends CrudServiceImpl<Product,Long> implements IProductService  {

    private final ProductRepository productRepository;

    @Override
    protected RepoGeneric<Product, Long> getRepo() {
        return productRepository;
    }

}
