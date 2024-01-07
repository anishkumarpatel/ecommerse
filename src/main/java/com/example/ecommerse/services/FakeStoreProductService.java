package com.example.ecommerse.services;

import com.example.ecommerse.dtos.FakeStoreProductDto;
import com.example.ecommerse.models.Category;
import com.example.ecommerse.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.HashAttributeSet;
import java.util.*;

@Service
public class FakeStoreProductService implements ProductService {
    RestTemplate restTemplate;
    HashMap<Long,FakeStoreProductDto> map = new HashMap<>();

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

        System.out.println("productDto : "+productDto);
         if(productDto == null)
             return convertFakeStoreProductToProduct(map.get(id));
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
            map.put(dto.getId(),dto);
        }
        for (Map.Entry<Long,FakeStoreProductDto> entry : map.entrySet())
            answer.add(convertFakeStoreProductToProduct(entry.getValue()));
        return answer;
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory().toString());
        dto.setImage(product.getImageUrl());
        map.put(product.getId(),dto);
        return product;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
         map.remove(id);
        FakeStoreProductDto dto = new FakeStoreProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory().toString());
        dto.setImage(product.getImageUrl());
        map.put(id,dto);
        return product;
    }

    public void  deleteProduct(Long id){
           map.remove(id);
    }

    @Override
    public Set<String> getAllCatagories() {
        Set<String> set = new HashSet<>();
         for(Map.Entry<Long,FakeStoreProductDto> entry : map.entrySet()){
             FakeStoreProductDto dto = entry.getValue();
            set.add(dto.getCategory());
         }
        return set;
    }
}
