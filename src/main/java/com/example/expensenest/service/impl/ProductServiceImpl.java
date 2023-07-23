package com.example.expensenest.service.impl;

import com.example.expensenest.entity.Products;
import com.example.expensenest.repository.ProductRepository;
import com.example.expensenest.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    public ProductServiceImpl (ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }
    public List<Products> getProductsByCategory(int categoryId) {
        return productRepository.getProductsByCategory(categoryId);
    }

    public boolean addProduct(Products products) {
        return productRepository.addProduct(products);
    }

    public List<Products> searchProductsByQuery (int categoryId, String query) {
        return productRepository.searchProductsByQuery(categoryId, query);
    }
}
