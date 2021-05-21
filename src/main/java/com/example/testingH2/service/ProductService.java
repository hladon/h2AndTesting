package com.example.testingH2.service;

import com.example.testingH2.entity.Product;
import com.example.testingH2.repository.ProductRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean checkIsProductExist(Product product) throws Exception {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("productId");
        Example<Product> example = Example.of(product, modelMatcher);
        if (!productRepository.exists(example))
            return false;
        return true;
    }
}
