package com.example.ecommerse.services;

import com.example.ecommerse.dtos.FakeStoreProductDto;
import com.example.ecommerse.models.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product getSingleProduct(Long id);

    List<Product> getAllProducts();

    Product addNewProduct(Product product);
    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);

    Set<String> getAllCatagories();
}
