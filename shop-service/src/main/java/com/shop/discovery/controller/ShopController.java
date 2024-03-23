package com.shop.discovery.controller;

import com.shop.discovery.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final String CUSTOMER_SERVICE_URL = "http://localhost:8083/api/customer";
    private final String ORDER_SERVICE_URL = "http://localhost:8081/api/order";
    private final String PRODUCT_SERVICE_URL = "http://localhost:8081/api/product";
    private final String ARTICLE_SERVICE_URL = "http://localhost:8082/api/article";

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Customer Section
     */
    // Get All Customers
    @GetMapping("/customers")
    public List<CustomerResponse> getAllCustomers() {
        CustomerResponse[] customers = restTemplate.getForObject(CUSTOMER_SERVICE_URL, CustomerResponse[].class);
        return Arrays.asList(customers);
    }

    // Create Customer
    @PostMapping("/customers")
    public CustomerResponse createCustomer(@RequestBody CustomerRequest request) {
        return restTemplate.postForObject(CUSTOMER_SERVICE_URL, request, CustomerResponse.class);
    }

    // Get Customer by ID
    @GetMapping("/customers/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id) {
        return restTemplate.getForObject(CUSTOMER_SERVICE_URL + "/" + id, CustomerResponse.class);
    }

    // Update Customer
    @PutMapping("/customers/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerRequest request) {
        restTemplate.put(CUSTOMER_SERVICE_URL + "/" + id, request);
    }

    // Delete Customer
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        restTemplate.delete(CUSTOMER_SERVICE_URL + "/" + id);
    }


    /**
     * Order and Products
     */

    // Create Product
    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return restTemplate.postForObject(PRODUCT_SERVICE_URL, request, ProductResponse.class);
    }

    // Get All Products
    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        ProductResponse[] products = restTemplate.getForObject(PRODUCT_SERVICE_URL, ProductResponse[].class);
        return Arrays.asList(products);
    }

    // Get Single Product by ID
    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + id, ProductResponse.class);
    }

    // Update Product
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        restTemplate.put(PRODUCT_SERVICE_URL + "/" + id, request);
    }

    // Delete Product
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        restTemplate.delete(PRODUCT_SERVICE_URL + "/" + id);
    }

    // Get All Orders
    @GetMapping("/orders")
    public List<OrderResponse> getAllOrders() {
        OrderResponse[] orders = restTemplate.getForObject(ORDER_SERVICE_URL, OrderResponse[].class);
        return Arrays.asList(orders);
    }

    // Order Endpoints
    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        return restTemplate.postForObject(ORDER_SERVICE_URL, request, OrderResponse.class);
    }

    @GetMapping("/orders/{id}")
    public OrderResponse getOrder(@PathVariable Long id) {
        return restTemplate.getForObject(ORDER_SERVICE_URL + "/" + id, OrderResponse.class);
    }

    @PutMapping("/orders/{id}")
    public OrderResponse updateOrder(@PathVariable Long id, @RequestBody OrderRequest request) {
        restTemplate.put(ORDER_SERVICE_URL + "/" + id, request);
        return getOrder(id);  // Fetch the updated order
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        restTemplate.delete(ORDER_SERVICE_URL + "/" + id);
    }

    /**
     * Article Section
     */
    // Get All Articles
    @GetMapping("/articles")
    public List<ArticleResponse> getAllArticles() {
        ArticleResponse[] articles = restTemplate.getForObject(ARTICLE_SERVICE_URL, ArticleResponse[].class);
        return Arrays.asList(articles);
    }

    // Article Endpoints
    @PostMapping("/articles")
    public ArticleResponse createArticle(@RequestBody ArticleRequest request) {
        return restTemplate.postForObject(ARTICLE_SERVICE_URL, request, ArticleResponse.class);
    }

    @GetMapping("/articles/{id}")
    public ArticleResponse getArticle(@PathVariable Long id) {
        return restTemplate.getForObject(ARTICLE_SERVICE_URL + "/" + id, ArticleResponse.class);
    }

    @PutMapping("/articles/{id}")
    public ArticleResponse updateArticle(@PathVariable Long id, @RequestBody ArticleRequest request) {
        restTemplate.put(ARTICLE_SERVICE_URL + "/" + id, request);
        return getArticle(id);  // Fetch the updated article
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        restTemplate.delete(ARTICLE_SERVICE_URL + "/" + id);
    }

}
