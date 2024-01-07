package com.example.ecommerse.services;

import com.example.ecommerse.dtos.FakeStoreProductDto;
import com.example.ecommerse.models.Category;
import com.example.ecommerse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    HashMap<Long, FakeStoreProductDto> map = new HashMap<>();

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        return product;
    }

    public Product getSingleProduct(Long id) {
        FakeStoreProductDto productDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class);
        return convertFakeStoreProductToProduct(productDto);
    }

    @Override
    public List<Product> getAllProducts() {

//        List<FakeStoreProductDto> response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                List<FakeStoreProductDto>.class
//        );

        FakeStoreProductDto[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> answer = new ArrayList<>();

        assert response != null;
        for (FakeStoreProductDto dto : response) {
            answer.add(convertFakeStoreProductToProduct(dto));
        }
        for (Map.Entry<Long,FakeStoreProductDto> entry : map.entrySet())
            answer.add(convertFakeStoreProductToProduct(entry.getValue()));
        return answer;
    }

    @Override
    public FakeStoreProductDto addNewProduct(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory().toString());
        dto.setImage(product.getImageUrl());
        map.put(product.getId(),dto);
        return dto;
    }
}
