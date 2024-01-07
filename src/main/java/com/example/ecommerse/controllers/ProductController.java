package com.example.ecommerse.controllers;

import com.example.ecommerse.dtos.FakeStoreProductDto;
import com.example.ecommerse.models.Product;
import com.example.ecommerse.services.FakeStoreProductService;
import com.example.ecommerse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
 private ProductService productService;
 private RestTemplate restTemplate ;

 @Autowired
 ProductController(ProductService productService, RestTemplate restTemplate){
   this.productService = productService;
   this.restTemplate = restTemplate;
 }
 @GetMapping() // localhost:8080/products
    public List<Product> getAllProducts(){

     return productService.getAllProducts();
 }
 @GetMapping("/{id}")
 public  Product getSingleProduct(@PathVariable("id") Long id){

     return  productService.getSingleProduct(id);
 }

 @PostMapping("/addproduct")
 public FakeStoreProductDto addNewProduct(@RequestBody Product product) {
     return  productService.addNewProduct(product) ;
 }
}
