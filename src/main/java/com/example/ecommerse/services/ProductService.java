package com.example.ecommerse.services;

import com.example.ecommerse.dtos.FakeStoreProductDto;
import com.example.ecommerse.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    FakeStoreProductDto addNewProduct(Product product);
}
