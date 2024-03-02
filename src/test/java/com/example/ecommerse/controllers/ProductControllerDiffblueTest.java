package com.example.ecommerse.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.ecommerse.models.Category;
import com.example.ecommerse.models.Product;
import com.example.ecommerse.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@ContextConfiguration(classes = {ProductController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @MockBean
    private RestTemplate restTemplate;

    /**
     * Method under test: {@link ProductController#getAllProducts()}
     */
    @Test
    void testGetAllProducts() throws Exception {
        // Arrange
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#getSingleProduct(Long)}
     */
    @Test
    void testGetSingleProduct() throws Exception {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setTitle("Dr");
        when(productService.getSingleProduct(Mockito.<Long>any())).thenReturn(product);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"price\":10.0,\"category\":{\"id\":1,\"name\":\"Name\"},\"description\":\"The characteristics"
                                        + " of someone or something\",\"imageUrl\":\"https://example.org/example\"}"));
    }

    /**
     * Method under test: {@link ProductController#addNewProduct(Product)}
     */
    @Test
    void testAddNewProduct() throws Exception {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setTitle("Dr");
        when(productService.addNewProduct(Mockito.<Product>any())).thenReturn(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImageUrl("https://example.org/example");
        product2.setPrice(10.0d);
        product2.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(product2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products/addproduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"price\":10.0,\"category\":{\"id\":1,\"name\":\"Name\"},\"description\":\"The characteristics"
                                        + " of someone or something\",\"imageUrl\":\"https://example.org/example\"}"));
    }

    /**
     * Method under test: {@link ProductController#replaceProduct(Long, Product)}
     */
    @Test
    void testReplaceProduct() throws Exception {
        // Arrange
        Category category = new Category();
        category.setId(1L);
        category.setName("Name");

        Product product = new Product();
        product.setCategory(category);
        product.setDescription("The characteristics of someone or something");
        product.setId(1L);
        product.setImageUrl("https://example.org/example");
        product.setPrice(10.0d);
        product.setTitle("Dr");
        when(productService.replaceProduct(Mockito.<Long>any(), Mockito.<Product>any())).thenReturn(product);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setName("Name");

        Product product2 = new Product();
        product2.setCategory(category2);
        product2.setDescription("The characteristics of someone or something");
        product2.setId(1L);
        product2.setImageUrl("https://example.org/example");
        product2.setPrice(10.0d);
        product2.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(product2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"price\":10.0,\"category\":{\"id\":1,\"name\":\"Name\"},\"description\":\"The characteristics"
                                        + " of someone or something\",\"imageUrl\":\"https://example.org/example\"}"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct2() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(Mockito.<Long>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{id}", 1L);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link ProductController#getAllCatagories()}
     */
    @Test
    void testGetAllCatagories() throws Exception {
        // Arrange
        when(productService.getAllCatagories()).thenReturn(new HashSet<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/catagories");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
